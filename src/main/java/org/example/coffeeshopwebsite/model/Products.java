package org.example.coffeeshopwebsite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "catId", referencedColumnName = "catId", foreignKey = @ForeignKey(name = "FK_Products_Categories"))
    private Categories categories;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", foreignKey = @ForeignKey(name = "FK_Products_Accounts"))
    private Accounts accounts;
    
    private String productName;
    private String productImage;

    @Column(length = 100)
    private String description;
    private Double sellingPrice;
    private Double discount;
    private Integer quantity;

    public Products() {}

    public Products(Long productId, Categories categories, Accounts accounts, String productName, String productImage, String description, Double sellingPrice, Double discount, Integer quantity) {
        this.productId = productId;
        this.categories = categories;
        this.accounts = accounts;
        this.productName = productName;
        this.productImage = productImage;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
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
}
