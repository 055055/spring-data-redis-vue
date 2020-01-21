package com.example.vue.entitiy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@ToString
@Builder
@RedisHash("goods")
public class Goods {
    @Id
    private String goodsId;

    private String goodsName;

    @Indexed
    private String goodsCode;

    private String goodsPrice;


}
