package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
