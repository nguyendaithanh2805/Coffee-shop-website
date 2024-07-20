package org.example.coffeeshopwebsite.model;

public class Cart {
    private int cartId;
    private int userId;
    private int productId;
    private int cartQuantity;
    private double totalBill;
    private Product product;

    public Cart() {
    }

    public Cart(int cartId, int userId, int productId, int cartQuantity,double totalBill, Product product) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.cartQuantity = cartQuantity;
        this.totalBill = totalBill;
        this.product = product;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}