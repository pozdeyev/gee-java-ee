package ru.geekbrains.persist;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductCategoryRepository {

    ProductCategory findById(long id);

    List<ProductCategory> findAll();


}
