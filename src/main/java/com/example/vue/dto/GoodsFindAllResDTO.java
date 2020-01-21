package com.example.vue.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class GoodsFindAllResDTO {

    private List<GoodsInfoResDTO> goodsInfoResDTO;

    private Long totalCount;

}
