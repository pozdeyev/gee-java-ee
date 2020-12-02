package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.ItemInCart;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.*;

@Stateful
public class CartService implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    private Map<ItemInCart, Integer> products;

    public CartService() {
        products = new HashMap<>();
    }

    public void addProduct(ProductRepresentative product, int count) {
        ItemInCart productIn = new ItemInCart(product);
        products.put(productIn, products.getOrDefault(productIn, 0) + count);
    }

    public void delProduct(ProductRepresentative product, int count) {
        ItemInCart productIn = new ItemInCart(product);
        int currCount = products.getOrDefault(productIn, 0);
        if (currCount - count > 0) {
            products.put(productIn, currCount - count);
        } else {
            products.remove(productIn);
        }
    }

    public List<ItemInCart> getAll() {
        products.forEach(ItemInCart::setCount);
        return new ArrayList<>(products.keySet());
    }
}



