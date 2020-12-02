package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository{

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public ProductCategory findById(long id) {
        return em.find(ProductCategory.class, id);
    }

    public List<ProductCategory> findAll() {
        return em.createQuery("from ProductCategory p", ProductCategory.class)
                .getResultList();
    }

}
