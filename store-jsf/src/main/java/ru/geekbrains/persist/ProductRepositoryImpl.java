package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepresentative;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    public void insert(Product product) {
        em.persist(product);
    }


    public void update(Product product) {
        em.merge(product);
    }


    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("from Product p", Product.class)
                .getResultList();
    }

    @Override
    public ProductRepresentative findProductReprById(long id) {
        return em.createQuery("select new ru.geekbrains.service.ProductRepresentative(p.id, p.title, p.description, p.price, c) " +
                "from Product p " +
                " left join p.productCategory c " +
                "where p.id = :id", ProductRepresentative.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ProductRepresentative> findAllProductRepr() {
        return em.createQuery("select new ru.geekbrains.service.ProductRepresentative(p.id, p.title, p.description, p.price, c) " +
                "from Product p " +
                " left join p.productCategory c ", ProductRepresentative.class)
                .getResultList();
    }


}