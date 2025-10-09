package com.exam.products.examen.service;
import com.exam.products.examen.dto.request.ProductRequest;
import com.exam.products.examen.dto.response.ProductResponse;
import com.exam.products.examen.entity.Product;
import com.exam.products.examen.repository.ProductRepository;
import com.exam.products.examen.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product createSampleProduct() {
        return Product.builder()
                .id(1L)
                .name("Laptop Gaming")
                .price(new BigDecimal("1299.99"))
                .stock(10)
                .active(true)
                .build();
    }

    private ProductRequest createSampleProductRequest() {
        return ProductRequest.builder()
                .name("Laptop Gaming")
                .price(new BigDecimal("1299.99"))
                .stock(10)
                .active(true)
                .build();
    }

    @Test
    void findByProduct_WhenProductExists_ShouldReturnProductResponse() {
        Long productId = 1L;
        Product product = createSampleProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductResponse result = productService.findByProduct(productId);

        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getStock(), result.getStock());
        assertEquals(product.getActive(), result.getActive());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void findByProduct_WhenProductNotExists_ShouldThrowException() {
        Long productId = 999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> productService.findByProduct(productId));
        assertEquals("Id not found", exception.getMessage());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void findByProduct_WhenIdIsNull_ShouldThrowException() {
        when(productRepository.findById(null)).thenThrow(new IllegalArgumentException("Id must not be null"));

        assertThrows(IllegalArgumentException.class, () -> productService.findByProduct(null));
    }

    @Test
    void createProduct_WithValidRequest_ShouldCreateAndReturnProduct() {
        ProductRequest productRequest = createSampleProductRequest();
        Product savedProduct = createSampleProduct();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductResponse result = productService.createProduct(productRequest);

        assertNotNull(result);
        assertEquals(savedProduct.getId(), result.getId());
        assertEquals(savedProduct.getName(), result.getName());
        assertEquals(savedProduct.getPrice(), result.getPrice());
        assertEquals(savedProduct.getStock(), result.getStock());
        assertEquals(savedProduct.getActive(), result.getActive());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_WithPartialData_ShouldCreateProduct() {
        ProductRequest productRequest = ProductRequest.builder()
                .name("Test Product")
                .price(new BigDecimal("99.99"))
                .stock(5)
                .build();

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(new BigDecimal("99.99"))
                .stock(5)
                .active(null)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductResponse result = productService.createProduct(productRequest);

        assertNotNull(result);
        assertEquals(savedProduct.getName(), result.getName());
        assertNull(result.getActive());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void findAllProduct_WhenProductsExist_ShouldReturnListOfProductResponses() {
        Product product1 = createSampleProduct();
        Product product2 = createSampleProduct();
        product2.setId(2L);
        product2.setName("Mouse Gaming");
        product2.setPrice(new BigDecimal("49.99"));

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<ProductResponse> result = productService.findAllProduct();

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(product1.getId(), result.get(0).getId());
        assertEquals(product1.getName(), result.get(0).getName());
        assertEquals(product1.getPrice(), result.get(0).getPrice());

        assertEquals(product2.getId(), result.get(1).getId());
        assertEquals(product2.getName(), result.get(1).getName());
        assertEquals(product2.getPrice(), result.get(1).getPrice());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findAllProduct_WhenNoProductsExist_ShouldReturnEmptyList() {
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        List<ProductResponse> result = productService.findAllProduct();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

}
