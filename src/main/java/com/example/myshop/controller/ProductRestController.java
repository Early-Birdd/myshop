package com.example.myshop.controller;

import com.example.myshop.domain.Product;
import com.example.myshop.domain.ProductMyPriceRequestDto;
import com.example.myshop.domain.ProductRepository;
import com.example.myshop.domain.ProductRequestDto;
import com.example.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @GetMapping("/api/products")
    public List<Product> readProducts(){

        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto productRequestDto){

        Product product = new Product(productRequestDto);

        return productRepository.save(product);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto productMyPriceRequestDto){

        return productService.update(id, productMyPriceRequestDto);
    }
}
