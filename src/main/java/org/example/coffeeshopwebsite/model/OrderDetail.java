package org.example.coffeeshopwebsite.model;

public class OrderDetail {
    private Product product;
    private Order order;
    private Double discount;
    private Integer quantity;
    private Double totalBill;

    public OrderDetail() {
    }

    public OrderDetail(Product product, Order order, Double discount, Integer quantity, Double totalBill) {
        this.product = product;
        this.order = order;
        this.discount = discount;
        this.quantity = quantity;
        this.totalBill = totalBill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }
}
