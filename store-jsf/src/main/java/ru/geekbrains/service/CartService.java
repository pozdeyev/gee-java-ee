package ru.geekbrains.service;

import ru.geekbrains.persist.ItemInCart;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartService implements Serializable {


    private final Map<ItemInCart, Integer> products;

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



