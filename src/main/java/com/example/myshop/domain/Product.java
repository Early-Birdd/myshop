package com.example.myshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    public Product(ProductRequestDto productRequestDto){
        this.title = productRequestDto.getTitle();
        this.link = productRequestDto.getLink();
        this.image = productRequestDto.getImage();
        this.lprice = productRequestDto.getLprice();
        this.myprice = 0; //사용자 지정 가격이 제일 낮다면 최저가 설정이 되지 않는다.
    }

    public void update(ProductMyPriceRequestDto productMyPriceRequestDto){
        this.myprice = productMyPriceRequestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto){
        this.lprice = itemDto.getLprice();
    }
}
