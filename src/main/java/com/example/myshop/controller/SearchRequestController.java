package com.example.myshop.controller;

import com.example.myshop.domain.ItemDto;
import com.example.myshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> execSearch(@RequestParam String query){

        String result = naverShopSearch.search(query);

        return naverShopSearch.fromJSONtoItems(result);
    }
}
