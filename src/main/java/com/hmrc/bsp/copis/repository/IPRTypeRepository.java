package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.IPRType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPRTypeRepository extends MongoRepository<IPRType, String> {
}
