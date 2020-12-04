package ru.geekbrains.controller;


import ru.geekbrains.persist.ItemInCart;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductRepresentative;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public List<ItemInCart> getAll() {
        return cartService.getAll();
    }

    public void addToCart(ProductRepresentative product) {
        cartService.addProduct(product, 1);
    }

    public void delFromCart(ProductRepresentative product) {
        cartService.delProduct(product, 1);
    }
}
