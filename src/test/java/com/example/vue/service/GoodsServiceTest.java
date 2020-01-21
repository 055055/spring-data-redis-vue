package com.example.vue.service;


import com.example.vue.dto.GoodsFindAllReqDTO;
import com.example.vue.dto.GoodsFindAllResDTO;
import com.example.vue.dto.GoodsInfoResDTO;
import com.example.vue.entitiy.Goods;
import com.example.vue.repository.GoodsRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
class GoodsServiceTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @After
    public void tearDown() throws Exception {
        goodsRepository.deleteAll();
    }


    @Test
    public void saveSuccessTest(){
        //given
        Goods goods = Goods.builder()
                .goodsName("커피")
                .goodsCode("coffee")
                .goodsPrice("3500")
                .build();
        //when
       Goods goodsResult = goodsRepository.save(goods);

        //then
        assertThat(goodsResult.getGoodsPrice()).isEqualTo("3500");
        assertThat(goodsResult.getGoodsCode()).isEqualTo("coffee");
        assertThat(goodsResult.getGoodsName()).isEqualTo("커피");

    }


    @Test
    public void findByGoodsCodeSuccessTest(){
        //given
        String goodsCode = "coffee";
        saveSuccessTest();

        //when
        Optional<Goods> result = goodsRepository.findByGoodsCode(goodsCode);

        //then
        Goods goods = result.orElseThrow(NullPointerException::new);
        assertThat(goods.getGoodsCode()).isEqualTo("coffee");
        assertThat(goods.getGoodsPrice()).isEqualTo("3500");

    }

    @Test
    public void updateGoodsSuccessTest(){
        //given
        saveSuccessTest();
        String goodsCode = "coffee";
        String goodsName = "맛좋은커피";
        String goodsPrice = "4000";

        //when
        Optional<Goods> result = goodsRepository.findByGoodsCode(goodsCode);
        Goods goods = result.orElseThrow(NullPointerException::new);
        goods.setGoodsName(goodsName);
        goods.setGoodsPrice(goodsPrice);
        goodsRepository.save(goods);

        //then
        assertThat(goods.getGoodsPrice()).isEqualTo("4000");
        assertThat(goods.getGoodsName()).isEqualTo("맛좋은커피");
        assertThat(goods.getGoodsCode()).isEqualTo("coffee");

    }

    @Test
    public void findByGoodsAllSuccessTest(){
        //given
        setUpSaveData();
        int page = 0;
        int size = 2;
        Pageable pageable = PageRequest.of(page,size);

        //when
        Page<Goods> all = goodsRepository.findAll(pageable);
        for (Goods result: all) {
            System.out.println(result.toString());
        }
    }


    @Test()
    public void deleteGoodsSuccessTest(){
       //given
        String goodsCode = "coffee";
        String exceptionMsg = "null!";
        saveSuccessTest();
        try {
            //when
            Optional<Goods> result = goodsRepository.findByGoodsCode(goodsCode);
            goodsRepository.delete(result.orElseThrow(NullPointerException::new));

            //then-a
            Optional<Goods> deleteCheck =  goodsRepository.findByGoodsCode(goodsCode);
            deleteCheck.orElseThrow(()-> new NullPointerException(exceptionMsg));
        }catch (Exception ex){
            //then-b
            assertThat(ex.getMessage()).isEqualTo(exceptionMsg);
        }

    }


    public void setUpSaveData(){
        List<Goods> saveAllList = new ArrayList<>();
        for (int i=0; i<3; i++){
            Goods goods = Goods.builder()
                    .goodsName("커피"+i)
                    .goodsCode("coffee"+i)
                    .goodsPrice("350"+i)
                    .build();
            saveAllList.add(goods);
        }
        goodsRepository.saveAll(saveAllList);

    }
}