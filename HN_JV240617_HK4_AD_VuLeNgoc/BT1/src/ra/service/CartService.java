package ra.service;

import ra.model.CartItem;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    private int nextCartItemId = 1;

    public void addProductToCart(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Số lượng không hợp lệ.");
            return;
        }

        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId().equals(product.getProductId())) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Cập nhật số lượng sản phẩm trong giỏ hàng.");
                return;
            }
        }

        if (product.getStock() < quantity) {
            System.out.println("Số lượng trong kho không đủ.");
            return;
        }

        CartItem newItem = new CartItem(nextCartItemId++, product, product.getProductPrice(), quantity);
        cartItems.add(newItem);
        product.setStock(product.getStock() - quantity);
        System.out.println("Sản phẩm đã được thêm vào giỏ hàng.");
    }

    public List<CartItem> getAllCartItems() {
        return cartItems;
    }

    public void updateCartItemQuantity(int cartItemId, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getCartItemId() == cartItemId) {
                Product product = item.getProduct();
                if (product.getStock() + item.getQuantity() < newQuantity) {
                    System.out.println("Số lượng trong kho không đủ.");
                    return;
                }
                product.setStock(product.getStock() + item.getQuantity() - newQuantity);
                item.setQuantity(newQuantity);
                System.out.println("Cập nhật số lượng sản phẩm trong giỏ hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong giỏ hàng với ID: " + cartItemId);
    }

    public void removeCartItem(int cartItemId) {
        for (CartItem item : cartItems) {
            if (item.getCartItemId() == cartItemId) {
                Product product = item.getProduct();
                product.setStock(product.getStock() + item.getQuantity());
                cartItems.remove(item);
                System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm trong giỏ hàng với ID: " + cartItemId);
    }

    public void clearCart() {
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            product.setStock(product.getStock() + item.getQuantity());
        }
        cartItems.clear();
        System.out.println("Giỏ hàng đã được xóa toàn bộ.");
    }
}
