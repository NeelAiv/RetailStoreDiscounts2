package com.example.RetailStoreDiscounts2;

import com.example.RetailStoreDiscounts2.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DiscountServiceTest {

    @Mock
    private User userMock;

    @Mock
    private Product product1Mock;

    @Mock
    private Product product2Mock;

    @Mock
    private Product product3Mock;

    private DiscountService discountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        discountService = new DiscountService();

        when(userMock.getName()).thenReturn("John Doe");
        when(userMock.getUserType()).thenReturn("employee");
        when(userMock.getJoinDate()).thenReturn(LocalDate.of(2020, 5, 10));

        when(product1Mock.getName()).thenReturn("Laptop");
        when(product1Mock.getPrice()).thenReturn(1000.0);
        when(product1Mock.isGrocery()).thenReturn(false);

        when(product2Mock.getName()).thenReturn("Apple");
        when(product2Mock.getPrice()).thenReturn(50.0);
        when(product2Mock.isGrocery()).thenReturn(true);

        when(product3Mock.getName()).thenReturn("TV");
        when(product3Mock.getPrice()).thenReturn(500.0);
        when(product3Mock.isGrocery()).thenReturn(false);
    }

    @Test
    public void testEmployeeDiscount() {
        Bill bill = new Bill(userMock, Arrays.asList(product1Mock, product2Mock, product3Mock));
        double netAmount = discountService.calculateNetAmount(bill);
        assertEquals(1025.0, netAmount);
    }

    @Test
    public void testAffiliateDiscount() {
        when(userMock.getUserType()).thenReturn("affiliate");

        Bill bill = new Bill(userMock, Arrays.asList(product1Mock, product2Mock, product3Mock));
        double netAmount = discountService.calculateNetAmount(bill);
        assertEquals(1325.0, netAmount);
    }

    @Test
    public void testLoyalCustomerDiscount() {
        when(userMock.getUserType()).thenReturn("customer");
        when(userMock.getJoinDate()).thenReturn(LocalDate.of(2018, 5, 10));

        Bill bill = new Bill(userMock, Arrays.asList(product1Mock, product2Mock, product3Mock));
        double netAmount = discountService.calculateNetAmount(bill);

        assertEquals(1400.0, netAmount, 0.01);
    }


    @Test
    public void testNoDiscount() {
        when(userMock.getUserType()).thenReturn("customer");
        when(userMock.getJoinDate()).thenReturn(LocalDate.of(2024, 1, 1));

        Bill bill = new Bill(userMock, Arrays.asList(product1Mock));
        double netAmount = discountService.calculateNetAmount(bill);
        assertEquals(950.0, netAmount);
    }
}
