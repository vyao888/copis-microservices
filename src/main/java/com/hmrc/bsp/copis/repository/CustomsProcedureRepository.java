package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.CustomsOffice;
import com.hmrc.bsp.copis.domain.reference.CustomsProcedure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomsProcedureRepository extends MongoRepository<CustomsProcedure, String> {
}
