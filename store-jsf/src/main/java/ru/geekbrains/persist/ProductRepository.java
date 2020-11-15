package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository {

    @Inject
    private ServletContext context;
    private Connection conn;

    @PostConstruct
    public void init() throws SQLException {
        this.conn = (Connection) context.getAttribute("jdbcConnection");
        createTableIfNotExists(conn);
    }


    public void insert(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into products (title, description, price) values (?, ?, ?);")) {
            stmt.setString(1, product.getTitle());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update products set title= ?,  description = ?, price = ? where id = ?;")) {
            stmt.setString(1, product.getTitle());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setLong(4, product.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from products where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Product findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title, description, price from products where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4));
            }
        }
        return new Product(-1L, "", "", null);
    }

    public List<Product> findAll() throws SQLException {
        List<Product> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title, description, price from products");
            while (rs.next()) {
                res.add(new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists products (\n" +
                    "\tid int auto_increment primary key,\n" +
                    "title varchar(25),\n" +
                    "    description varchar(255),\n" +
                    "    price decimal \n" +
                    ");");
        }
    }
}