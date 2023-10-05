package model;

import controler.ReadAndWriteFileProduct;
import controler.iProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements iProduct<Product> {
    private List<Product> productList;
    private ReadAndWriteFileProduct readAndWriteFileProduct = new ReadAndWriteFileProduct();


    public ProductManager() {
        productList = readAndWriteFileProduct.ReaFile();


    }

    public void addProduct(Product product) {
        productList.add(product);
        readAndWriteFileProduct.writeFile(productList);
    }


    public boolean removeProduct(String id) {
        boolean check = false;
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                productList.remove(product);
                readAndWriteFileProduct.writeFile(productList);
                check = true;
            }
        }
        return check;

    }

    @Override
    public Product findIndexById(String id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public boolean editProduct(String id, Product newProduct) {
        boolean check = false;
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                product.setProductName(newProduct.getProductName());
                product.setPrice(newProduct.getPrice());
                product.setQuantity(newProduct.getQuantity());
                product.setProductType(newProduct.getProductType());
                readAndWriteFileProduct.writeFile(productList);
                check = true;
            }
        }
        return check;
    }

    @Override
    public List<Product> findAll() {
        return this.productList;
    }

    @Override
    public List<Product> searchByName(String productName) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
    public List<Product> getByCategory(String type) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductType().equalsIgnoreCase(type)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }



}
