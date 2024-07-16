package com.example.RetailStoreDiscounts2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private String name;
    private double price;
    private boolean grocery;
}
