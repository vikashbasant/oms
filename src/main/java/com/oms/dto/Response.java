package com.oms.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private String message;
    private transient Object data;
}
