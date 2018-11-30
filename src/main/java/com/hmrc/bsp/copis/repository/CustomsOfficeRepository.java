package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.CustomsOffice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomsOfficeRepository extends MongoRepository<CustomsOffice, String> {
}
