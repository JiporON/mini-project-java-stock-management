package co.cstad;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private Integer productCode;
    private String productName;
    private Integer qty;
    private Double price;
    private LocalDate importDate;

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public Product() {
    }

    public Product(Integer productCode, String productName, Integer qty, Double price, LocalDate importDate) {
        this.productCode = productCode;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
        this.importDate = importDate;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", importDate=" + importDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productCode, product.productCode) && Objects.equals(productName, product.productName) && Objects.equals(qty, product.qty) && Objects.equals(price, product.price) && Objects.equals(importDate, product.importDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, productName, qty, price, importDate);
    }
}
