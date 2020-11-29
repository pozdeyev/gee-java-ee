package ru.geekbrains;

import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepo");
        if (productRepository == null) {
            throw new ServletException("Product repository is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/page_header").include(req, resp);

         try {
             Product product = productRepository.findById(getIdFromRequestAndConvertToLong(req, "id"));
            req.setAttribute("product", product);
                 getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").include(req, resp);
            } catch (SQLException ex) {
              ex.printStackTrace();
              }

    }


    private long getIdFromRequestAndConvertToLong(HttpServletRequest req, String id) {
        return Long.parseLong(req.getParameter(id));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
