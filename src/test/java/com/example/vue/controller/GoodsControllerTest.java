package com.example.vue.controller;

import com.example.vue.dto.GoodsAddReqDTO;
import com.example.vue.dto.GoodsAddResDTO;
import com.example.vue.dto.GoodsInfoResDTO;
import com.example.vue.dto.GoodsUpdReqDTO;
import com.example.vue.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(GoodsController.class)
public class GoodsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GoodsService goodsService;

    @Test
    public void addGoodsSuccessTest() throws Exception {
        //given
        GoodsAddReqDTO goodsAddReqDTO = new GoodsAddReqDTO();
        goodsAddReqDTO.setGoodsCode("testCode");
        goodsAddReqDTO.setGoodsName("testName");
        goodsAddReqDTO.setGoodsPrice("5000");

        GoodsAddResDTO goodsAddResDTO = new GoodsAddResDTO();
        goodsAddResDTO.setGoodsId("test-success");

        given(this.goodsService.addGoods(any(GoodsAddReqDTO.class))).willReturn(goodsAddResDTO);

        //when
        ResultActions resultActions =  mockMvc.perform(post("/goods")
                                        .accept(MediaType.APPLICATION_JSON_VALUE)
                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                                        .content(objectMapper.writeValueAsString(goodsAddReqDTO)))
                                        .andDo(print());
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("goodsId").value("test-success"));


    }

    @Test
    public void  findByGoodsSuccessTest() throws Exception {
        //given
        String goodsCode = "coffee";

        GoodsInfoResDTO goodsInfoResDTO = GoodsInfoResDTO.builder()
                                        .goodsPrice("3000")
                                        .goodsName("커피")
                                        .goodsCode("coffee")
                                        .build();

        given(this.goodsService.findByGoodsCode(any(String.class))).willReturn(goodsInfoResDTO);

        //when
       ResultActions resultActions = mockMvc.perform(get("/goods/"+goodsCode)
                                    .accept(MediaType.APPLICATION_JSON_VALUE)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                                    .andDo(print());

       //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("goodsPrice").value("3000"))
                .andExpect(jsonPath("goodsCode").value("coffee"))
                .andExpect(jsonPath("goodsName").value("커피"))
        ;

    }

    @Test
    public void updateGoodsSuccessTest() throws Exception {
        //given
        String goodsCode = "coffee";
        GoodsUpdReqDTO goodsUpdReqDTO = new GoodsUpdReqDTO();
        goodsUpdReqDTO.setUpdateGoodsCode("coffee");
        goodsUpdReqDTO.setUpdateGoodsName("커피");
        goodsUpdReqDTO.setUpdateGoodsPrice("3500");

        //given(this.goodsService.updateGoods(any(GoodsUpdReqDTO.class), any(String.class)))

        //when
        ResultActions resultActions =  mockMvc.perform(patch("/goods/"+goodsCode)
                                        .accept(MediaType.APPLICATION_JSON_VALUE)
                                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                                        .content(objectMapper.writeValueAsString(goodsUpdReqDTO)))
                                        .andDo(print());

        //then
        resultActions
                .andExpect(status().isOk())
                ;
    }






}