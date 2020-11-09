package ru.geekbrains.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;


@WebListener
public class AppBootstrapListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing application");

        ServletContext sc = sce.getServletContext();
        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            Connection connection = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute("jdbcConnection", connection);
            ProductRepository productRepository = new ProductRepository(connection);
            sc.setAttribute("productRepo", productRepository);

            if (productRepository.findAll().size()==0){
                productRepository.insert(new Product(-1L, "Banana", "Очень вкусные бананы", BigDecimal.valueOf(56)));
                productRepository.insert(new Product(-1L, "Pineapple", "Ананас африканский", BigDecimal.valueOf(110)));
                productRepository.insert(new Product(-1L, "Apple", "Красные яблоки", BigDecimal.valueOf(80)));
                productRepository.insert(new Product(-1L, "Melon", "Замечательная дыня", BigDecimal.valueOf(180)));
                productRepository.insert(new Product(-1L, "Watermelon", "Сочный арбуз", BigDecimal.valueOf(150)));
                productRepository.insert(new Product(-1L, "Pear", "Спелая груша", BigDecimal.valueOf(95)));
                productRepository.insert(new Product(-1L, "Peach", "Персик сладкий", BigDecimal.valueOf(120)));
                productRepository.insert(new Product(-1L, "Kiwi", "Кислая киви", BigDecimal.valueOf(105)));
                productRepository.insert(new Product(-1L, "Mango", "Манго дорогая", BigDecimal.valueOf(350)));
            }

        } catch (SQLException ex) {
            logger.error("Can't initialize JDBC connection", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Closing JDBC connection");
        ServletContext sc = sce.getServletContext();
        Connection connection = (Connection) sc.getAttribute("jdbcConnection");
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            logger.info("Can't close JDBC connection", ex);
        }
    }
}
