package com.oms.dto;

import lombok.Data;


@Data
public class ProductDTO {
    private String name;
    private int availableQuantity;
    private float price;
}
