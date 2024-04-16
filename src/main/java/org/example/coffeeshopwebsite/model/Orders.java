package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "customerId", foreignKey = @ForeignKey(name = "FK_orders_customers"))
    private Customers customers;


    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", foreignKey = @ForeignKey(name = "FK_orders_accounts"))
    private Accounts accounts;
    private Date orderDate;
    private Date deliveryDate;
    private Boolean status = false;
    private String note;

    @OneToMany(mappedBy = "orders")
    private List<OrdersDetail> orderDetails;

    public Orders() {}

    public Orders(Long orderId, Customers customers, Accounts accounts, Date orderDate, Date deliveryDate, Boolean status, String note, List<OrdersDetail> orderDetails) {
        this.orderId = orderId;
        this.customers = customers;
        this.accounts = accounts;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.note = note;
        this.orderDetails = orderDetails;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrdersDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrdersDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}


