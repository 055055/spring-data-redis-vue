package com.example.vue.controller;

import com.example.vue.dto.*;
import com.example.vue.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;



//@CrossOrigin(value = "http://localhost:8080")
@Slf4j
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Cacheable(value = "goodsCache",key = "#goodsCode")
    @GetMapping(value = "/{goodsCode}")
    public GoodsInfoResDTO findByGoodsCode(@PathVariable String goodsCode){
        long startTime = System.currentTimeMillis();
        System.out.println("start ");
        long endTime = System.currentTimeMillis();
        GoodsInfoResDTO goodsInfoResDTO = goodsService.findByGoodsCode(goodsCode);
        System.out.println("how long time : "+Long.toString(endTime-startTime));
        return goodsInfoResDTO;
    }

   // @Cacheable(value = "goodsCacheAll")
    @GetMapping
    public GoodsFindAllResDTO findGoodsAll(GoodsFindAllReqDTO goodsFindAllReqDTO){
        System.out.println("goodsFindAll  :"+goodsFindAllReqDTO.toString());
        return goodsService.findByGoodsAll(goodsFindAllReqDTO);

    }

    @PostMapping
    public GoodsAddResDTO addGoods(@RequestBody  GoodsAddReqDTO goodsAddReqDTO){
        System.out.println(goodsAddReqDTO.toString());
        return goodsService.addGoods(goodsAddReqDTO);
    }

    @CacheEvict(value = "goodsCache",key = "#goodsCode")
    @PutMapping(value = "/{goodsCode}")
    public void updateGoods(@RequestBody GoodsUpdReqDTO goodsUpdReqDTO,@PathVariable String goodsCode){
        System.out.println("goodsUpdReq : "+goodsUpdReqDTO.toString());
        System.out.println("goodsCode :"+goodsCode );
        goodsService.updateGoods(goodsUpdReqDTO,goodsCode);
    }

    @DeleteMapping(value = "/{goodsCode}")
    public void deleteGoods(@PathVariable String goodsCode){
        System.out.println("deleteGoods : "+goodsCode);
        goodsService.deleteGoods(goodsCode);
    }

    @Cacheable(value = "cacheTest",key = "#testId")
    @GetMapping("/test/{testId}")
    public GoodsInfoResDTO test(@PathVariable String testId){
        long startTime = System.currentTimeMillis();
        System.out.println("start ");
        long endTime = System.currentTimeMillis();
        GoodsInfoResDTO result = GoodsInfoResDTO.builder()
                                .goodsName("엽기 떡볶이")
                                .goodsCode("Y101")
                                .goodsPrice("10000")
                                .build();
        System.out.println("endTime - startTime : "+Long.toString(endTime-startTime));
        return result;
    }

}
