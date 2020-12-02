package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.Future;

@Local
public interface ProductServiceLocal {

    void insert(ProductRepresentative productRepresentative);

    void update(ProductRepresentative productRepresentative);

    void delete(long id);

    ProductRepresentative findById(long id);

    List<ProductRepresentative> findAll();

    Future<ProductRepresentative> findByIdAsync(long id);


}
