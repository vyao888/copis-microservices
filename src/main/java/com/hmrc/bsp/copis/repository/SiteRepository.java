package com.hmrc.bsp.copis.repository;


import com.hmrc.bsp.copis.domain.reference.Site;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteRepository extends MongoRepository<Site, String> {
}
