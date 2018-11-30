package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.TypeOfPlace;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeOfPlaceRepository extends MongoRepository<TypeOfPlace, String> {
}
