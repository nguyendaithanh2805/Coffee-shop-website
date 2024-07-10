package org.example.coffeeshopwebsite.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private int categoryId;
    private int adminId;
    private String name;
    private String description;
    private BigDecimal discount;
    private String image;
    private int quantity;
    private BigDecimal sellingPrice;

    public Product() {
    }

    public Product(int productId, int categoryId, int adminId, String name, String description, BigDecimal discount, String image, int quantity, BigDecimal sellingPrice) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.adminId = adminId;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.image = image;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
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

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
