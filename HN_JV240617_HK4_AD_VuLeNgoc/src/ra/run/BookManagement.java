package ra.run;

import ra.business.entity.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CatalogService catalogService = new CatalogService();
    private static final ProductService productService = new ProductService();

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    catalogManagement();
                    break;
                case 2:
                    productManagement();
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("**************************BASIC-MENU**************************");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát");
        System.out.println("**************************************************************");
        System.out.print("Lựa chọn của bạn: ");
    }

    private static void catalogManagement() {
        int nextCatalogId = 1;

        while (true) {
            showCatalogMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập số danh mục cần thêm: ");
                    int numOfCatalogs = Integer.parseInt(scanner.nextLine());

                    for (int i = 0; i < numOfCatalogs; i++) {
                        Catalog catalog = new Catalog();


                        catalog.setCatalogId(nextCatalogId);
                        nextCatalogId++;

                        System.out.print("Nhập tên danh mục: ");
                        catalog.setCatalogName(scanner.nextLine());
                        System.out.print("Nhập mô tả danh mục: ");
                        catalog.setDescriptions(scanner.nextLine());


                        catalogService.save(catalog);
                    }
                    break;
                case 2:
                    List<Catalog> catalogs = catalogService.getAll();
                    if (catalogs.isEmpty()) {
                        System.out.println("Không có danh mục nào.");
                    } else {

                        System.out.printf("%-10s %-30s %-50s%n", "ID", "Tên danh mục", "Mô tả");
                        System.out.println("---------------------------------------------------------------");
                        for (Catalog catalog : catalogs) {

                            System.out.printf("%-10d %-30s %-50s%n",
                                    catalog.getCatalogId(),
                                    catalog.getCatalogName(),
                                    catalog.getDescriptions());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã danh mục cần sửa: ");
                    int catalogId = Integer.parseInt(scanner.nextLine());


                    Catalog catalog = catalogService.findById(catalogId);


                    if (catalog == null) {
                        System.out.println("Không tìm thấy danh mục với mã: " + catalogId);
                        break;
                    }


                    System.out.print("Nhập tên mới cho danh mục: ");
                    String newName = scanner.nextLine();

                    catalogService.updateCatalogName(catalogId, newName);
                    break;


                case 4:
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    catalogId = Integer.parseInt(scanner.nextLine());
                    catalogService.delete(catalogId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void showCatalogMenu() {
        System.out.println("********************CATALOG-MANAGEMENT********************");
        System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
        System.out.println("2. Hiển thị thông tin tất cả các danh mục");
        System.out.println("3. Sửa tên danh mục theo mã danh mục");
        System.out.println("4. Xóa danh mục theo mã danh mục");
        System.out.println("5. Quay lại");
        System.out.println("*********************************************************");
        System.out.print("Lựa chọn của bạn: ");
    }

    private static void productManagement() {
        while (true) {
            showProductMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Nhập số sản phẩm cần thêm: ");
                    int numOfProducts = Integer.parseInt(scanner.nextLine());

                    for (int i = 0; i < numOfProducts; i++) {
                        try {
                            Product product = new Product();

                            System.out.print("Nhập mã sản phẩm: ");
                            product.setProductId(scanner.nextLine());

                            System.out.print("Nhập tên sản phẩm: ");
                            product.setProductName(scanner.nextLine());

                            System.out.print("Nhập giá sản phẩm: ");
                            product.setProductPrice(Double.parseDouble(scanner.nextLine()));

                            System.out.print("Nhập mô tả sản phẩm: ");
                            product.setDescription(scanner.nextLine());

                            System.out.print("Nhập số lượng tồn kho: ");
                            product.setStock(Integer.parseInt(scanner.nextLine()));

                            System.out.print("Nhập trạng thái sản phẩm (true/false): ");
                            product.setStatus(Boolean.parseBoolean(scanner.nextLine()));

                            productService.save(product);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                            break;
                        } catch (Exception e) {
                            System.out.println("Có lỗi xảy ra: " + e.getMessage());
                            break;
                        }
                    }
                    break;

                case 2:
                    List<Product> products = productService.getAll();
                    if (products.isEmpty()) {
                        System.out.println("Không có sản phẩm nào.");
                    } else {

                        System.out.printf("%-10s %-20s %-10s %-30s %-10s %-10s%n", "ID", "Tên sản phẩm", "Giá", "Mô tả", "Số lượng", "Trạng thái");
                        System.out.println("--------------------------------------------------------------------------------------------");
                        for (Product product : products) {

                            System.out.printf("%-10s %-20s %-10.2f %-30s %-10d %-10b%n",
                                    product.getProductId(),
                                    product.getProductName(),
                                    product.getProductPrice(),
                                    product.getDescription(),
                                    product.getStock(),
                                    product.isStatus());
                        }
                    }
                    break;

                case 3:
                    productService.sortProductsByPriceDescending();
                    break;
                case 4:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String productId = scanner.nextLine();
                    productService.delete(productId);
                    break;
                case 5:
                    System.out.print("Nhập tên sản phẩm cần tìm: ");
                    String name = scanner.nextLine();
                    List<Product> foundProducts = productService.searchByName(name);
                    for (Product product : foundProducts) {
                        System.out.println(product);
                    }
                    break;
                case 6:
                    System.out.print("Nhập mã sản phẩm cần cập nhật: ");
                   productId = scanner.nextLine();
                    Product existingProduct = productService.findById(productId);

                    if (existingProduct != null) {
                        try {
                            System.out.print("Nhập tên mới của sản phẩm: ");
                            existingProduct.setProductName(scanner.nextLine());

                            System.out.print("Nhập giá mới của sản phẩm: ");
                            existingProduct.setProductPrice(Double.parseDouble(scanner.nextLine()));

                            System.out.print("Nhập mô tả mới của sản phẩm: ");
                            existingProduct.setDescription(scanner.nextLine());

                            System.out.print("Nhập số lượng tồn kho mới: ");
                            existingProduct.setStock(Integer.parseInt(scanner.nextLine()));

                            System.out.print("Nhập trạng thái sản phẩm mới (true/false): ");
                            existingProduct.setStatus(Boolean.parseBoolean(scanner.nextLine()));

                            productService.updateProduct(productId, existingProduct);
                            System.out.println("Sản phẩm đã được cập nhật thành công.");

                        } catch (IllegalArgumentException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                            break;
                        } catch (Exception e) {
                            System.out.println("Có lỗi xảy ra: " + e.getMessage());
                            break;
                        }
                    } else {
                        System.out.println("Không tìm thấy sản phẩm với mã: " + productId);
                    }
                    break;

                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void showProductMenu() {
        System.out.println("********************PRODUCT-MANAGEMENT********************");
        System.out.println("1. Nhập số sản phẩm thêm mới và nhập thông tin sản phẩm");
        System.out.println("2. Hiển thị thông tin các sản phẩm");
        System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
        System.out.println("4. Xóa sản phẩm theo mã");
        System.out.println("5. Tìm kiếm sách theo tên sách");
        System.out.println("6. Thay đổi thông tin của sách theo mã sách");
        System.out.println("7. Quay lại");
        System.out.println("*********************************************************");
        System.out.print("Lựa chọn của bạn: ");
    }
}
