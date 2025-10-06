package com.exam.products.examen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="products")
@Builder
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotEmpty(message = "The product required name")
   @NotNull(message = "The name must not be null")
   private String name;

   @Min(value=0 , message = "The price must not be negative")
   private BigDecimal price;

   @Min(value=0 , message = "The stock must not be negative")
   private Integer stock;

   private Boolean active;

}
