package com.example.RetailStoreDiscounts2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String name;
    private String userType;
    private LocalDate joinDate;
}
