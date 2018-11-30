package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.CountryCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryCodeRepository extends MongoRepository<CountryCode, String> {
}
