package com.example.vue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsAddReqDTO {

    @JsonProperty(value = "goodsName")
    private String goodsName;
    @JsonProperty(value = "goodsCode")
    private String goodsCode;
    @JsonProperty(value = "goodsPrice")
    private String goodsPrice;

}
