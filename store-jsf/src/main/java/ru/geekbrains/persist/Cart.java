package ru.geekbrains.persist;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;



public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private List<ItemInCart> cartList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItemInCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<ItemInCart> cartList) {
        this.cartList = cartList;
    }
}
