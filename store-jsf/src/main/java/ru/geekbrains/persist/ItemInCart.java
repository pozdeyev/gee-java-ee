package ru.geekbrains.persist;
import ru.geekbrains.service.ProductRepresentative;

import java.io.Serializable;
import java.util.Objects;


public class ItemInCart implements Serializable {

    private ProductRepresentative product;

    private Integer count;

    public ProductRepresentative getProduct() {
        return product;
    }

    public void setProduct(ProductRepresentative product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ItemInCart(ProductRepresentative  product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemInCart that = (ItemInCart) o;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

}
