package com.example.vue.service;

import com.example.vue.dto.*;
import com.example.vue.entitiy.Goods;
import com.example.vue.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public GoodsAddResDTO addGoods(GoodsAddReqDTO goodsAddReqDTO) {
        Goods goods = Goods.builder()
                        .goodsName(goodsAddReqDTO.getGoodsName())
                        .goodsCode(goodsAddReqDTO.getGoodsCode())
                        .goodsPrice(goodsAddReqDTO.getGoodsPrice())
                        .build();
        goodsRepository.save(goods);
        return new GoodsAddResDTO(goods.getGoodsId());
    }

    public GoodsInfoResDTO findByGoodsCode(String goodsCode) {
        Optional<Goods> result = goodsRepository.findByGoodsCode(goodsCode);
        Goods goods = result.orElseThrow(NullPointerException::new);
        GoodsInfoResDTO resDTO = GoodsInfoResDTO.builder()
                                    .goodsCode(goods.getGoodsCode())
                                    .goodsPrice(goods.getGoodsPrice())
                                    .goodsName(goods.getGoodsName())
                                    .build();
        return resDTO;
    }

    public GoodsFindAllResDTO findByGoodsAll(GoodsFindAllReqDTO goodsFindAllReqDTO) {
        Pageable pageable = PageRequest.of(goodsFindAllReqDTO.getPage(),goodsFindAllReqDTO.getSize());
        Page<Goods> all = goodsRepository.findAll(pageable);
         //Iterable<Goods> all = goodsRepository.findAll();
        List<GoodsInfoResDTO> returnList = new ArrayList<>();

        for (Goods result: all) {
            GoodsInfoResDTO goodsInfoResDTO = GoodsInfoResDTO.builder()
                                                .goodsCode(result.getGoodsCode())
                                                .goodsPrice(result.getGoodsPrice())
                                                .goodsName(result.getGoodsName())
                                                .build();
            returnList.add(goodsInfoResDTO);
        }

        GoodsFindAllResDTO goodsFindAllResDTO  = GoodsFindAllResDTO.builder()
                                                .goodsInfoResDTO(returnList)
                                                .totalCount(all.getTotalElements())
                                                .build();


        return goodsFindAllResDTO;
    }


    public void updateGoods(GoodsUpdReqDTO goodsUpdReqDTO, String goodsCode) {
        Optional<Goods> result = goodsRepository.findByGoodsCode(goodsCode);
          Goods goods = result.orElseThrow(NullPointerException::new);
          goods.setGoodsName(goodsUpdReqDTO.getUpdateGoodsName());
          goods.setGoodsPrice(goodsUpdReqDTO.getUpdateGoodsPrice());
          goodsRepository.save(goods);
    }

    public void deleteGoods(String goodsCode) {
        //goodsRepository.deleteByGoodsCode(goodsCode);  //I don't know why this Query method is not supported.
      Optional<Goods> result = goodsRepository.findByGoodsCode(goodsCode);
      goodsRepository.delete(result.orElseThrow(NullPointerException::new));
    }
}
