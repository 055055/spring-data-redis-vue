package com.example.vue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsUpdReqDTO {

    @JsonProperty(value = "updateGoodsName")
    private String updateGoodsName;
    @JsonProperty(value = "updateGoodsCode")
    private String updateGoodsCode;
    @JsonProperty(value = "updateGoodsPrice")
    private String updateGoodsPrice;
}
