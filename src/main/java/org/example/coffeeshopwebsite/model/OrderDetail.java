package org.example.coffeeshopwebsite.model;

public class OrderDetail {
    private Product product;
    private int ProductId;
    private Order order;
    private int OrderId;
    private double discount;
    private int orderQuantity;
    private double totalBill;

    public OrderDetail() {
    }

    public OrderDetail(Product product, int productId, Order order, int orderId, double discount, int orderQuantity, double totalBill) {
        this.product = product;
        ProductId = productId;
        this.order = order;
        OrderId = orderId;
        this.discount = discount;
        this.orderQuantity = orderQuantity;
        this.totalBill = totalBill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
}
