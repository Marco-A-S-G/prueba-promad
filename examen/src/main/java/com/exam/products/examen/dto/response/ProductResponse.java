package com.exam.products.examen.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ProductResponse {

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Boolean active;
}
