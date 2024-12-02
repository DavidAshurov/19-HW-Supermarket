package supermarket;

import supermarket.dao.Supermarket;
import supermarket.dao.SupermarketImpl;
import supermarket.model.Product;

import java.time.LocalDate;

public class SupermarketApp {
    public static void main(String[] args) {
        Supermarket supermarket = new SupermarketImpl();
        supermarket.addProduct(new Product(1,"Milk","Diary","Prostokvashino",50.9, LocalDate.of(2024,12,2)));
        supermarket.addProduct(new Product(2,"Sour cream","Diary","Prostokvashino",70.0, LocalDate.of(2024,12,20)));
        supermarket.addProduct(new Product(3,"Butter","Diary","House in the village",120.6, LocalDate.of(2024,12,1)));
        supermarket.addProduct(new Product(4,"Beef steak","Meat","Miratorg",550.9, LocalDate.of(2024,12,10)));
        supermarket.addProduct(new Product(5,"Sprite","Drinks","The Coca-Cola",30.7, LocalDate.of(2025,12,2)));
        supermarket.addProduct(new Product(6,"Chips","Snacks","Lay's",80.9, LocalDate.of(2023,12,2)));
        printProducts(supermarket,"All products");
        System.out.println("SKU Quantity is " + supermarket.skuQuantity());
        System.out.println("Result of adding product with existing barcode is " + supermarket.addProduct(new Product(3,null,null,null,0,null)));
        System.out.println("=====Product with barcode 4=====");
        System.out.println(supermarket.findByBarcode(4));
        printProducts(supermarket.findByCategory("Diary"),"Diary products");
        printProducts(supermarket.findByBrand("Prostokvashino"),"Prostokvashino products");
        Iterable<Product> productsToRemove = supermarket.findProductsWithExpiredDate();
        printProducts(productsToRemove,"Expired products");
        for (Product product : productsToRemove) {
            supermarket.removeProduct(product.getBarcode());
        }
        printProducts(supermarket,"All products after removing expired products");
    }

    public static void printProducts(Iterable<Product> products,String title) {
        System.out.println("=====" + title + "=====");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
