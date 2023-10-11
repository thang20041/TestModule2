package model;

import controler.ReadAndWriteFileProduct;
import controler.iProduct;

import java.util.List;

public class ProductManager implements iProduct<Product> {
    private List<Product> productList;
    private ReadAndWriteFileProduct readAndWriteFileProduct = new ReadAndWriteFileProduct();


    public ProductManager() {
        productList = readAndWriteFileProduct.ReaFile();


    }
    public boolean isIDExists(String id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
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
    public void sortProductsByPrice(boolean ascending) {
        productList.sort((p1, p2) -> {
            if (ascending) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            } else {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
    }

    public Product findMaxPriceProduct() {
        if (productList.isEmpty()) {
            return null;
        }

        Product findMaxPriceProduct = productList.get(0);
        for (Product product : productList) {
            if (product.getPrice() > findMaxPriceProduct.getPrice()) {
                findMaxPriceProduct = product;
            }
        }

        return findMaxPriceProduct;
    }





}
