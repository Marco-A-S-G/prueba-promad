package com.exam.products.examen.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    @NotEmpty(message = "The product required name")
    @NotNull(message = "The name must not be null")
    private String name;

    @Min(value=0 , message = "The price must not be negative")
    private BigDecimal price;

    @Min(value=0 , message = "The stock must not be negative")
    private Integer stock;

    private Boolean active;
}
