package model;

public class Product {
    private String id;
    private String productName;
    private int quantity;
    private double price;
    private String productType;

    public Product(String id, String productName, int quantity, double price, String productType) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productType = productType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ID: " + id + "  -  Tên: " + productName + "  -  Số lượng: " + quantity + "  -  Giá: " + price + "  -  Loại: " + productType;
    }

    public String getData() {
        return id + "," + productName + "," + quantity + "," + price + "," + productType;
    }
}
