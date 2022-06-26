package com.example.myshop.service;

import com.example.myshop.domain.ItemDto;
import com.example.myshop.domain.Product;
import com.example.myshop.domain.ProductMyPriceRequestDto;
import com.example.myshop.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Long update(Long id, ProductMyPriceRequestDto productMyPriceRequestDto){

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );

        product.update(productMyPriceRequestDto);

        return id;
    }

    @Transactional
    public Long updateBySearch(Long id, ItemDto itemDto){

        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        product.updateByItemDto(itemDto);

        return id;
    }
}
