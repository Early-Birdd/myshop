package com.example.myshop.utils;

import com.example.myshop.domain.ItemDto;
import com.example.myshop.domain.Product;
import com.example.myshop.domain.ProductRepository;
import com.example.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Scheduler {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final NaverShopSearch naverShopSearch;

    //초, 분, 시, 일, 월, 주
    @Scheduled(cron = "0 0 1 * * *")
    public void updatedPrice() throws InterruptedException{

        System.out.println("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for(int i=0; i<productList.size(); i++){

            TimeUnit.SECONDS.sleep(1); //naver에서 짧은 시간 내 요청은 막아버림, 1초에 한 상품씩 조회
            Product p = productList.get(i);
            String title = p.getTitle();
            String resultString = naverShopSearch.search(title);
            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(resultString);
            ItemDto itemDto = itemDtoList.get(0);
            Long id = p.getId();
            productService.updateBySearch(id, itemDto);
        }
    }
}
