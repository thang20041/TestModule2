package view;

import model.Product;
import model.ProductManager;
import validate.Validate;

import java.util.List;
import java.util.Scanner;

public class Menu {
    ProductManager productManager = new ProductManager();
    Scanner inputInt = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);
    Scanner scanner = new Scanner(System.in);

    public void productMenu() {
        int choice;
        do {
            System.out.println("---Quản Lý Sản Phẩm---");
            System.out.println("1.Hiển thị tất cả danh sách sản phẩm.");
            System.out.println("2.Thêm mới sản phẩm. ");
            System.out.println("3.Sửa thông tin sản phẩm.");
            System.out.println("4.Xóa sản phẩm.");
            System.out.println("5.Sắp xếp");
            System.out.println("6.Tìm sản phẩm có giá đắt nhất ");
            System.out.println("0.Thoát ứng dụng.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = Validate.checkChoice();
            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    showAdd();
                    break;
                case 3:
                    showEditProduct();
                    break;
                case 4:
                    showDelete();

                    break;
                case 5:
                    showSortProducts();
                    break;
                case 6:
                    showFindMaxPriceProduct();
                    break;
                case  0:
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn 0 để quay lại Menu.");
                    scanner.nextLine();
                    choice = -1;
            }

        } while (choice != 0);
    }

    public void showAdd() {
        System.out.println("---Thêm sản phẩm mới---");
        System.out.println("Nhập thông tin chi tiết sản phẩm:");
        System.out.print("ID (3 hoặc nhiều chữ số): ");
        String id = Validate.checkID(productManager);
        System.out.print("Tên sản phẩm (6-8 kí tự): ");
        String name = Validate.checkName();
        System.out.print("Số lượng sản phẩm (nhỏ hơn 100): ");
        int quantity = Validate.checkQuantity();
        System.out.print("Giá sản phẩm (trên 10000 đồng): ");
        double price = Validate.checkPrice();
        System.out.print("Mô tả sản phẩm: ");
        String describeProduct = inputString.nextLine();
        productManager.addProduct(new Product(id, name, quantity, price, describeProduct));
        System.out.println("THÊM SẢN PHẨM THÀNH CÔNG !!");
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }

    public void showDelete() {
        System.out.println("---Xóa Sản Phẩm Khỏi Danh Sách---");
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String deleteID = inputString.nextLine();
        Product productToDelete = productManager.findIndexById(deleteID);
        if (productToDelete == null) {
            System.out.println("Không tìm thấy sản phẩm có ID  " + deleteID + " để xóa:))");
        } else {
            System.out.println("Sản phẩm được tìm thấy:");
            System.out.println("Sản phẩm được tìm thấy:");
            System.out.println("ID: " + productToDelete.getId());
            System.out.println("Tên: " + productToDelete.getProductName());
            System.out.println("Giá: " + productToDelete.getPrice());
            System.out.println("Số lượng: " + productToDelete.getQuantity());
            System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (Y/N): ");
            String confirmation = inputString.nextLine();
            if (confirmation.equalsIgnoreCase("Y")) {
                boolean isDeleted = productManager.removeProduct(deleteID);
                if (isDeleted) {
                    System.out.println("Xóa sản phẩm thành công.");
                } else {
                    System.out.println("Xóa sản phẩm không thành công.");
                }
            } else {
                System.out.println("Xóa sản phẩm đã bị hủy.");
            }
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();
    }


    public void showAll() {
        List<Product> products = productManager.findAll();

        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào.");
        } else {
            System.out.println("===== DANH SÁCH TẤT CẢ SẢN PHẨM =====");
            for (Product product : products) {
                System.out.println(product);

            }
        }
    }

    public void showEditProduct() {
        System.out.println("---Sửa Sản Phẩm Trong Danh Sách---");
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        String id = inputString.nextLine();
        Product existingProduct = productManager.findIndexById(id);
        if (existingProduct == null) {
            System.out.println("Không tìm thấy sản phẩm có ID: " + id);
            return;
        }
        System.out.println("Nhập chi tiết sản phẩm được cập nhật:");
        System.out.print("Tên sản phẩm (6-8 kí tự): ");
        String name = Validate.checkName();
        existingProduct.setProductName(name);
        System.out.print("Số lượng sản phẩm (nhỏ hơn 100): ");
        int quantity = Validate.checkQuantity();
        existingProduct.setQuantity(quantity);
        System.out.print("Giá sản phẩm (trên 10.000 đồng): ");
        double price = Validate.checkPrice();
        existingProduct.setPrice(price);
        System.out.print("Mô tả sản phẩm: ");
        String productType = inputString.nextLine();
        existingProduct.setProductType(productType);
        System.out.println("SỬA THÀNH CÔNG !!");
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();
    }

    private void showSortProducts() {
        System.out.print("Sắp xếp theo giá : \n" +
                "1. Tăng dần\n" +
                "2. Giảm dần\n ");
        int sortOption = inputInt.nextInt();
        boolean ascending = (sortOption == 1);
        productManager.sortProductsByPrice(ascending);
        System.out.println("Sản phẩm được sắp xếp theo giá.");
        List<Product> sortedProducts = productManager.findAll();
        for (Product product: sortedProducts) {
            System.out.println(product);
        }

        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();
    }

    private void showFindMaxPriceProduct() {
        System.out.println("---Tìm kiếm sản phẩm giá đắt nhất---");
        Product findMaxPriceProduct = productManager.findMaxPriceProduct();
        if (findMaxPriceProduct != null) {
            System.out.println("Sản phẩm đắt nhất:");
            System.out.println("ID: " + findMaxPriceProduct.getId());
            System.out.println("Name: " + findMaxPriceProduct.getProductName());
            System.out.println("Price: " + findMaxPriceProduct.getPrice());
        } else {
            System.out.println("Không tìm thấy sản phẩm nào.");
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();
    }
}
