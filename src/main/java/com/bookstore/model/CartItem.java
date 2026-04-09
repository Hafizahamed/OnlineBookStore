package com.bookstore.model;

public class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQty() {
        quantity++;
    }

    public void decreaseQty() {
        if(quantity > 1) quantity--;
    }
}