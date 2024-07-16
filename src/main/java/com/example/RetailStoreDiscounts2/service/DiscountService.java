package com.example.RetailStoreDiscounts2.service;

import com.example.RetailStoreDiscounts2.Bill;
import com.example.RetailStoreDiscounts2.Product;
import com.example.RetailStoreDiscounts2.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountService {

    public double calculateNetAmount(Bill bill) {
        User user = bill.getUser();
        List<Product> products = bill.getProducts();
        if (products == null || products.isEmpty()) {
            return 0.0;
        }

        double total = products.stream().mapToDouble(Product::getPrice).sum();
        double groceryTotal = products.stream().filter(Product::isGrocery).mapToDouble(Product::getPrice).sum();

        System.out.println("Total: " + total);
        System.out.println("Grocery Total: " + groceryTotal);

        double discount = getDiscount(user, total, groceryTotal);
        System.out.println("Discount: " + discount);

        double addedDiscount = (int)(total / 100) * 5;
        System.out.println("Added Discount: " + addedDiscount);

        double netAmount = total - discount - addedDiscount;
        System.out.println("Net Amount: " + netAmount);

        return netAmount;
    }

    private double getDiscount(User user, double total, double groceryTotal) {
        if (user == null) {
            return 0.0;
        }

        double discount = 0.0;
        double nonGroceryTotal = total - groceryTotal;
        String userType = user.getUserType();
        if ("employee".equalsIgnoreCase(userType)) {
            discount = 0.3 * nonGroceryTotal;
        } else if ("affiliate".equalsIgnoreCase(userType)) {
            discount = 0.1 * nonGroceryTotal;
        } else if (ChronoUnit.YEARS.between(user.getJoinDate(), LocalDate.now()) > 2) {
            discount = 0.05 * nonGroceryTotal;
        }

        return discount;
    }

}
