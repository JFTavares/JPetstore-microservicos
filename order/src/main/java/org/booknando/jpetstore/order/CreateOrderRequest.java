package org.booknando.jpetstore.order;

import org.booknando.jpetstore.order.domain.Account;
import org.booknando.jpetstore.order.domain.Cart;

public class CreateOrderRequest {

    private Cart cart;
    private Account account;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
