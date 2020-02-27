package com.everson.thl.repository;

import com.everson.thl.domain.Hedge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HedgeRepository extends MongoRepository<Hedge , String> {

}
