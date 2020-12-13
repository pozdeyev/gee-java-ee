package ru.geekbrains.service;

import ru.geekbrains.persist.ProductCategory;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRepresentative implements Serializable {
    private Long id;

    @NotEmpty
    private String title;

    private String description;

    @NotNull
    private BigDecimal price;

    private Long categoryId;

    private String categoryName;

    public ProductRepresentative() {

    }

    public ProductRepresentative(Long id, String title, String description, BigDecimal price, ProductCategory productCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.categoryId = productCategory != null ? productCategory.getId() : null;
        this.categoryName = productCategory != null ? productCategory.getName() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
