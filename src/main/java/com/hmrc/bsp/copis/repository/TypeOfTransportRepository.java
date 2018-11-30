package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.TypeOfTransport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeOfTransportRepository extends MongoRepository<TypeOfTransport, String> {
}
