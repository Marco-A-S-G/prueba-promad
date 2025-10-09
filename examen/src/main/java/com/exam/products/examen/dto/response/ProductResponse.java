package com.exam.products.examen.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Boolean active;
}
