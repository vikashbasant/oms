package com.oms.dto;

import com.oms.entity.ShopingCart;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String orderDescription;
    private List<ShopingCart> cartItems;
    private String customerEmail;
    private String customerName;
}
