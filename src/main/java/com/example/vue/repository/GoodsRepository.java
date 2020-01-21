package com.example.vue.repository;


        import com.example.vue.entitiy.Goods;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.repository.CrudRepository;

        import java.util.Optional;

public interface GoodsRepository extends CrudRepository<Goods,String>  {
    Optional<Goods> findByGoodsCode(String goodsCode);

    Page<Goods> findAll(Pageable pageable);

    void deleteByGoodsCode(String goodsCode);
}
