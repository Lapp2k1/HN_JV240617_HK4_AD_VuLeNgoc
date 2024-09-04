package ra.model;

import ra.business.entity.Catalog;

public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status = true;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.setProductPrice(productPrice);
        this.setDescription(description);
        this.setStock(stock);
        this.setCatalog(catalog);
        this.setStatus(status);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        if (productId != null && productId.matches("P\\d{4}")) {
            this.productId = productId;
        } else {
            throw new IllegalArgumentException("Product ID phải bắt đầu bằng chữ P và theo sau là 4 ký tự số");
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName != null && !productName.trim().isEmpty()) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Product Name không được để trống");
        }
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        if (productPrice > 0) {
            this.productPrice = productPrice;
        } else {
            throw new IllegalArgumentException("Product Price phải lớn hơn 0");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 10) {
            this.stock = stock;
        } else {
            throw new IllegalArgumentException("Stock phải ít nhất là 10");
        }
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        if (catalog != null) {
            this.catalog = catalog;
        } else {
            throw new IllegalArgumentException("Catalog không được để null");
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String productStatus = status ? "Bán" : "Không bán";
        return "Product ID: " + productId +
                ", Product Name: " + productName +
                ", Price: " + productPrice +
                ", Description: " + description +
                ", Stock: " + stock +
                ", Catalog: " + catalog.getCatalogName() +
                ", Status: " + productStatus;
    }
}
