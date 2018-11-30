package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.ActionType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionTypeRepository extends MongoRepository<ActionType, String> {
}
