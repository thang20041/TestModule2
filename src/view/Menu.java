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

    public void productMenu() {
        int choice;
        do {
            System.out.println("---Quản Lý Sản Phẩm---");
            System.out.println("1.Thêm mới sản phẩm. ");
            System.out.println("2.Xóa sản phẩm.");
            System.out.println("3.Sửa thông tin sản phẩm.");
            System.out.println("4.Tìm kiếm sản phẩm theo Id.");
            System.out.println("5.Tìm kiếm sản phẩm theo tên. ");
            System.out.println("6.Hiển thị tất cả danh sách sản phẩm.");
            System.out.println("7.Tìm kiếm sản phẩm theo loại.");
            System.out.println("0.Thoát ứng dụng.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = Validate.checkChoice();
            switch (choice) {
                case 1:
                    showAdd();
                    break;
                case 2:
                    showDelete();
                    break;
                case 3:
                    showEditProduct();
                    break;
                case 4:
                    showSearchById();
                    break;
                case 5:
                    showSearchByName();
                    break;
                case 6:
                    showAll();
                    break;
                case 7:
                    showProductByType();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;

            }
        } while (choice != 0);
    }

    public void showAdd() {
        System.out.println("---Thêm sản phẩm mới---");
        System.out.println("Nhập thông tin chi tiết sản phẩm:");
        System.out.print("ID (3 hoặc nhiều chữ số): ");
        String id = Validate.checkID();
        System.out.print("Tên sản phẩm (6-8 kí tự): ");
        String name = Validate.checkName();
        System.out.print("Số lượng sản phẩm (nhỏ hơn 100): ");
        int quantity = Validate.checkQuantity();
        System.out.print("Giá sản phẩm (trên 10000 đồng): ");
        double price = Validate.checkPrice();
        System.out.print("Loại Sản phẩm: ");
        String productType = inputString.nextLine();
        productManager.addProduct(new Product(id, name, quantity, price, productType));
        System.out.println("THÊM SẢN PHẨM THÀNH CÔNG !!");

    }

    public void showDelete() {
        System.out.println("---Xóa Sản Phẩm Khỏi Danh Sách---");
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String id = Validate.checkID();
        boolean isDeleted = productManager.removeProduct(id);
        if (isDeleted) {
            System.out.println("Xóa sản phẩm thành công.");
        } else {
            System.out.println("Không tìm thấy sản pẩm có ID  " + id + " để xóa:))");
        }

    }

    public void showAll() {
        List<Product> products = productManager.findAll();

        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào.");
        } else {
            System.out.println("===== DANH SÁCH TẤT CẢ SẢN PHẨM =====");
            for (Product product : products) {
                System.out.println("ID: " + product.getId());
                System.out.println("Tên sản phẩm: " + product.getProductName());
                System.out.println("Số lượng sản phẩm : " + product.getQuantity());
                System.out.println("Giá : " + product.getPrice());
                System.out.println("Loại Sản phẩm: " + product.getProductType());
                System.out.println("===============================");
            }
        }

    }

    public void showEditProduct() {
        System.out.println("---Sửa Sản Phẩm Trong Danh Sách---");
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        String id = Validate.checkID();
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
        System.out.print("Giá sản phẩm (trên 10000 đồng): ");
        double price = Validate.checkPrice();
        existingProduct.setPrice(price);
        System.out.print("Loại Sản phẩm: ");
        String productType = inputString.nextLine();
        existingProduct.setProductType(productType);
        System.out.println("SỬA THÀNH CÔNG !!");
    }

    public void showSearchById() {
        System.out.println("---Tim Kiếm Sản Phẩm Theo ID---");
        System.out.print("Nhập ID sản phẩm cần tìm: ");
        String id = Validate.checkID();
        if (productManager.findIndexById(id) != null) {
            System.out.println(" =>>>Đã tìm thấy sản phẩm: " + productManager.findAll());
        } else {
            System.out.println("=>>> Không tìm thấy sản phẩm với ID đã cho :-(");
        }
    }

    public void showSearchByName() {
        System.out.println("---Tim Kiếm Sản Phẩm Theo Tên---");
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = inputString.nextLine();
        System.out.println("Sản phẩm có tên " + name + " là: ");
        if (!productManager.searchByName(name).isEmpty()) {
            for (Product product : productManager.searchByName(name)) {
                System.out.println(product.getData());
            }
        } else {
            System.out.println("không tìm thấy sản phẩm có tên " + name + " :-(");
        }

    }
    public void showProductByType() {
        System.out.println("------ TÌM SẢN PHẨM THEO LOẠI-----");
        System.out.println("Loại: 'Thuốc lá' hoăc 'Nước ngọt'");
        System.out.print("Nhập loại sản phẩm: ");
        String type = inputString.nextLine();

        List<Product> productList = productManager.getByCategory(type);

        if (!productList.isEmpty()) {
            System.out.println("Danh sách sản phẩm thuộc loại " + type + ":");
            for (Product product : productList) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không có sản phẩm nào thuộc loại " + type + ".");
        }


    }
}
