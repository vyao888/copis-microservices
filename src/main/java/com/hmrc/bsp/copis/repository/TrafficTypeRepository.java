package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.TrafficType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrafficTypeRepository extends MongoRepository<TrafficType, String> {
}
