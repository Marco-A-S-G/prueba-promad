package com.exam.products.examen.service.impl;

import com.exam.products.examen.dto.request.ProductRequest;
import com.exam.products.examen.dto.response.ProductResponse;
import com.exam.products.examen.entity.Product;
import com.exam.products.examen.repository.ProductRepository;
import com.exam.products.examen.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly =true )
    public ProductResponse findByProduct(Long id) {

        Product product =  productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Id not found"));
        return productMapper(product);

    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = productRequestMapper(productRequest);
        return productMapper(productRepository.save(product));

    }

    @Override
    public List<ProductResponse> findAllProduct() {
        List<Product> products =  productRepository.findAll();

        return products.stream()
                .map(this::productMapper)
                .collect(Collectors.toList());

    }

    private Product productRequestMapper(ProductRequest productRequest) {

            return Product.builder()
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .stock(productRequest.getStock())
                    .active(productRequest.getActive())
                    .build();
    }

    public ProductResponse productMapper (Product product){

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .active(product.getActive())
                .stock(product.getStock())
                .build();


    }
}
