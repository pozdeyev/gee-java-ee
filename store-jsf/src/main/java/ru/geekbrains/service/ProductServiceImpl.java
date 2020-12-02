package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductCategory;
import ru.geekbrains.persist.ProductCategoryRepository;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
public class ProductServiceImpl implements ProductServiceLocal, ProductServiceRemote {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductCategoryRepository productCategoryRepository;

    @TransactionAttribute
    @Override
    public void insert(ProductRepresentative productRepresentative) {
        ProductCategory category = productCategoryRepository.findById(productRepresentative.getCategoryId());
        productRepository.insert(new Product (null, productRepresentative.getTitle(), productRepresentative.getDescription(),productRepresentative.getPrice(), category));
    }

    @TransactionAttribute
    @Override
    public void update(ProductRepresentative productRepresentative) {
        ProductCategory category = productCategoryRepository.findById(productRepresentative.getCategoryId());
        productRepository.update(new Product (productRepresentative.getId(), productRepresentative.getTitle(), productRepresentative.getDescription(),productRepresentative.getPrice(), category));
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductRepresentative findById(long id) {
        return productRepository.findProductReprById(id);
    }

    @Override
    public List<ProductRepresentative> findAll() {
        return productRepository.findAllProductRepr();
    }

    @Asynchronous
    @Override
    public Future<ProductRepresentative> findByIdAsync(long id) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<>(productRepository.findProductReprById(id));
    }

}
