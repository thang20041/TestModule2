package view;

import model.Product;
import model.ProductManager;

import java.util.List;
import java.util.Scanner;

public class Menu {
    ProductManager productManager = new ProductManager();
    Scanner inputInt = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);
    Scanner inputDouble = new Scanner(System.in);

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
            System.out.println("0.Thoát ứng dụng.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputInt.nextInt();
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

            }
        } while (choice != 0);
    }
    public void showAdd(){
        System.out.println("---Thêm sản phẩm mới---");
        System.out.println("Nhập thông tin chi tiết sản phẩm:");
        System.out.print("ID (3 hoặc nhiều chữ số): ");
        String id = inputString.nextLine();
        System.out.print("Tên sản phẩm (6-8 kí tự): ");
        String nameProduct = inputString.nextLine();
        System.out.print("Số lượng sản phẩm (nhỏ hơn 100): ");
        int quantity = inputInt.nextInt();
        System.out.print("Giá sản phẩm (dưới 1000): ");
        double price = inputDouble.nextDouble();
        inputDouble.nextLine();
        System.out.print("Loại Sản phẩm: ");
        String productType = inputString.nextLine();
        Product product = new Product(id, nameProduct, quantity, price, productType);
        productManager.addProduct(product);
        System.out.println("Đã thêm sản phẩm thành công!");
    }
    public void showDelete(){
        System.out.println("---Xóa Sản Phẩm Khỏi Danh Sách---");
        System.out.print("Nhập ID sản phẩm cần xóa: ");

        String deleteId = inputString.nextLine();

        boolean isDeleted = productManager.removeProduct(deleteId);

        if (isDeleted) {
            System.out.println("Xóa sản phẩm thành công.");
        } else {
            System.out.println("Không tìm thấy sản pẩm có ID  " + deleteId + " để xóa:))");
        }

    }

    public  void showAll(){
        List<Product> products = productManager.findAll();

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

    public void showEditProduct(){
        System.out.println("---Sửa Sản Phẩm Trong Danh Sách---");
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        String editId = inputString.nextLine();

        Product existingProduct = productManager.findIndexById(editId);

        if (existingProduct == null) {
            System.out.println("Không tìm thấy sản phẩm có ID: " + editId);
            return;
        }

        System.out.println("Nhập chi tiết sản phẩm được cập nhật:");
        System.out.print("Tên sản phẩm (6-8 kí tự): ");
        String nameProduct = inputString.nextLine();
        System.out.print("Số lượng sản phẩm (nhỏ hơn 100): ");
        int quantity = inputInt.nextInt();
        System.out.print("Giá sản phẩm (dưới 1000): ");
        double price = inputDouble.nextDouble();
        inputDouble.nextLine();
        System.out.print("Loại Sản phẩm: ");
        String category = inputString.nextLine();
        Product editProduct = new Product(editId, nameProduct, quantity, price, category);
        productManager.editProduct(editId,editProduct);
        System.out.println("Sản phẩm được cập nhật thành công!");

    }
    public void showSearchById() {
        System.out.println("---Tim Kiếm Sản Phẩm Theo ID---");
        System.out.print("Nhập ID sản phẩm cần tìm: ");
        String id = inputString.nextLine();
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
}
