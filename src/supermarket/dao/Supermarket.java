package supermarket.dao;

import supermarket.model.Product;

public interface Supermarket extends Iterable<Product>{
    boolean addProduct(Product product);
    Product removeProduct(long barcode);
    Product findByBarcode(long barcode);
    Iterable<Product> findByCategory(String category);
    Iterable<Product> findByBrand(String brand);
    Iterable<Product> findProductsWithExpiredDate();
    int skuQuantity();
}
