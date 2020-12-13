package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepresentative;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductRepository {


    void insert(Product product);


    void update(Product product);


    void delete(long id);

    Product findById(long id);

    List<Product> findAll();

    ProductRepresentative findProductReprById(long id);

    List<ProductRepresentative> findAllProductRepr();

}