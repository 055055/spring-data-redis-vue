package com.example.vue.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoodsFindAllReqDTO {


    private int page;

    private int size;

    private String sort;

}
