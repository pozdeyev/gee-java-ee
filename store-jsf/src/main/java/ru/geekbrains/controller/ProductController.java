package ru.geekbrains.controller;

import ru.geekbrains.persist.*;

import ru.geekbrains.service.ProductRepresentative;
import ru.geekbrains.service.ProductServiceLocal;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductServiceLocal productService;

    @Inject
    private ProductCategoryRepository productCategoryRepository;


    private ProductRepresentative product;

    private List<ProductRepresentative> products;

    private List<ProductCategory> productCategories;


    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.products = productService.findAll();
        this.productCategories=productCategoryRepository.findAll();
    }

    public List<ProductRepresentative> getAllProducts() {
        return products;
    }

    public ProductRepresentative getProduct() {
        return product;
    }

    public void setProduct(ProductRepresentative product) {
        this.product = product;
    }

    public String editProduct(ProductRepresentative product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductRepresentative product) {
        productService.delete(product.getId());
    }

    public String saveProduct() {
        if (product.getId() == null) {
            productService.insert(product);
        } else {
            productService.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new ProductRepresentative();
        return "/product.xhtml?faces-redirect=true";
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "/index.xhtml?faces-redirect=true";
    }


    public ProductServiceLocal getProductService() {
        return productService;
    }

    public void setProductService(ProductServiceLocal productService) {
        this.productService = productService;
    }

    public ProductCategoryRepository getProductCategoryRepository() {
        return productCategoryRepository;
    }

    public void setProductCategoryRepository(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductRepresentative> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRepresentative> products) {
        this.products = products;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }
}
