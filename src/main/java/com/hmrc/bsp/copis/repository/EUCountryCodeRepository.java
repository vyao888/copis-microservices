package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.EUCountryCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EUCountryCodeRepository extends MongoRepository<EUCountryCode, String> {
}
