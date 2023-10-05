package controler;

import model.Product;

import java.util.List;

public interface iProduct<T> {
    void addProduct(T t);
    boolean removeProduct(String id);
    Product findIndexById(String id);
    boolean editProduct(String id, T t);
    List<T> findAll();
    List<T> searchByName(String productName);

}
