package com.oms.dto;

import lombok.Data;

@Data
public class ResponseOrderDTO {
    private float amount;
    private int invoiceNumber;
    private String date;
    private String orderDescription;
    private int orderId;
}
