package com.example.vue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
public class GoodsInfoResDTO implements Serializable {

    private static final long serialVersionUID = -2387924030150310014L;

    @JsonProperty(value = "goodsName")
    private String goodsName;
    @JsonProperty(value = "goodsCode")
    private String goodsCode;
    @JsonProperty(value = "goodsPrice")
    private String goodsPrice;

}
