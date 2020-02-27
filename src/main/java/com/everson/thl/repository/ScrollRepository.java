package com.everson.thl.repository;

import com.everson.thl.domain.Scroll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScrollRepository extends MongoRepository<Scroll, String> {


      void deleteByScrollNumber(Integer scrollNumber);
}
