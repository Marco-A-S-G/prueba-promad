package com.exam.products.examen.service;

import com.exam.products.examen.dto.request.ProductRequest;
import com.exam.products.examen.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse findByProduct(Long id);
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> findAllProduct();
}
