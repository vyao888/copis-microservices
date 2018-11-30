package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.CategoryOfGoods;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryOfGoodsRepository extends MongoRepository<CategoryOfGoods, String> {
}
