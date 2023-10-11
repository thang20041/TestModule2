package model;

public class Product {
    private String id;
    private String productName;
    private int quantity;
    private double price;
    private String describeProduct;

    public Product(String id, String productName, int quantity, double price, String describeProduct) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.describeProduct = describeProduct;
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
        return describeProduct;
    }

    public void setProductType(String productType) {
        this.describeProduct = productType;
    }

    @Override
    public String toString() {
        return "ID: " + id + "  -  Tên: " + productName + "  -  Số lượng: " + quantity + "  -  Giá: " + price + "  -  Mô tả: " + describeProduct;
    }

    public String getData() {
        return id + "," + productName + "," + quantity + "," + price + "," + describeProduct;
    }
}
