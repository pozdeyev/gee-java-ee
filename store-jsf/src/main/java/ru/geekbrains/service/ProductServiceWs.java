package ru.geekbrains.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    List<ProductRepresentative> findAll();

    @WebMethod
    ProductRepresentative findById(long id);

    @WebMethod
    void insert (ProductRepresentative productRepresentative);

}
