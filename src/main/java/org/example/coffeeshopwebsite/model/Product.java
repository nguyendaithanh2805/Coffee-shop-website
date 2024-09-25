package org.example.coffeeshopwebsite.model;

public class Product {
    private int productId;
    private int categoryId;
    private int userId;
    private String name;
    private String description;
    private double discount;
    private String image;
    private int quantity;
    private double sellingPrice;
    private int cartQuantity;

    public Product() {
    }

    public Product(int productId, int categoryId, int userId, String name, String description, double discount, String image, int quantity, double sellingPrice, int cartQuantity) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.image = image;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.cartQuantity = cartQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
