package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_OrdersDetail")
public class OrdersDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", foreignKey = @ForeignKey(name = "FK_OrdersDetail_Orders"))
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId", foreignKey = @ForeignKey(name = "FK_OrdersDetail_Products"))
    private Products products;
    
    private Integer quantity;
    private Double sellingPrice;
    private Double Discount;

    public OrdersDetail() {}

    public OrdersDetail(Orders orders, Products products, Integer quantity, Double sellingPrice, Double discount) {
        this.orders = orders;
        this.products = products;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        Discount = discount;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }
}
