package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.UnitOfMeasure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnitOfMeasureRepository extends MongoRepository<UnitOfMeasure, String> {
}
