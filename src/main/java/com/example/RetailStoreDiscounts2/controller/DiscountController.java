package com.example.RetailStoreDiscounts2.controller;

import com.example.RetailStoreDiscounts2.Bill;
import com.example.RetailStoreDiscounts2.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculate")
    public double calculateNetPayableAmount(@RequestBody Bill bill) {
        return discountService.calculateNetAmount(bill);
    }
}
