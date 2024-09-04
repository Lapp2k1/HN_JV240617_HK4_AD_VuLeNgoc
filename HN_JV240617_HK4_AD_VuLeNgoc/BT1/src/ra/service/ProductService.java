package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductService implements IGenericService<Product, String> {
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        try {
            products.add(product);
            System.out.println("Product đã được thêm thành công.");
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @Override
    public Product findById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với mã: " + productId);
        return null;
    }

    @Override
    public void delete(String productId) {
        try {
            Product productToRemove = findById(productId);
            if (productToRemove != null) {
                products.remove(productToRemove);
                System.out.println("Sản phẩm đã được xóa.");
            }
        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra khi xóa sản phẩm: " + e.getMessage());
        }
    }

    public void sortProductsByPriceDescending() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getProductPrice(), p1.getProductPrice());
            }
        });
        System.out.println("Danh sách sản phẩm đã được sắp xếp theo giá giảm dần.");
    }

    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(name)) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm với tên: " + name);
        }
        return result;
    }

    public void updateProduct(String productId, Product updatedProduct) {
        Product product = findById(productId);
        if (product != null) {
            try {
                product.setProductName(updatedProduct.getProductName());
                product.setProductPrice(updatedProduct.getProductPrice());
                product.setDescription(updatedProduct.getDescription());
                product.setStock(updatedProduct.getStock());
                product.setCatalog(updatedProduct.getCatalog());
                product.setStatus(updatedProduct.isStatus());
                System.out.println("Thông tin sản phẩm đã được cập nhật.");
            } catch (IllegalArgumentException e) {
                System.out.println("Thông tin sản phẩm không hợp lệ: " + e.getMessage());
            }
        }
    }
}
