package com.exam.products.examen.api;

import com.exam.products.examen.dto.request.ProductRequest;
import com.exam.products.examen.dto.response.ProductResponse;
import com.exam.products.examen.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @GetMapping("stock/{id}")
    public ResponseEntity<ProductResponse> stock(@NotNull(message = "El id no debe de ser null") @PathVariable Long id){

        return new ResponseEntity<>(productService.findByProduct(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest){

            return  new ResponseEntity<>(productService.createProduct(productRequest),HttpStatus.CREATED);
    }

}
