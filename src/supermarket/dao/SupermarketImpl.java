package supermarket.dao;

import supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SupermarketImpl implements Supermarket{
    private List<Product> products;
    private int skuQuantity;

    public SupermarketImpl() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null || findByBarcode(product.getBarcode()) != null) {
            return false;
        }
        products.add(product);
        skuQuantity++;
        return true;
    }

    @Override
    public Product removeProduct(long barcode) {
        Product product = findByBarcode(barcode);
        if (product != null) {
            products.remove(product);
        }
        return product;
    }

    @Override
    public Product findByBarcode(long barcode) {
        for (Product product : products) {
            if (product.getBarcode() == barcode) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        return findProductsByPredicate(prod -> prod.getCategory().equals(category));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        return findProductsByPredicate(prod -> prod.getBrand().equals(brand));
    }

    @Override
    public Iterable<Product> findProductsWithExpiredDate() {
        return findProductsByPredicate(prod -> prod.getExpDate().isBefore(LocalDate.now()));
    }

    private Iterable<Product> findProductsByPredicate(Predicate<Product> predicate) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product product : products) {
            if (predicate.test(product)) {
                res.add(product);
            }
        }
        return res;
    }

    @Override
    public int skuQuantity() {
        return skuQuantity;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
