package com.example.vue.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GoodsAddResDTO {
    private String goodsId;

    public GoodsAddResDTO(String goodsId) {
        this.goodsId = goodsId;
    }
}
