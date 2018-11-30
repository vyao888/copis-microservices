package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.Intervention;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterventionRepository extends MongoRepository<Intervention, String> {
}
