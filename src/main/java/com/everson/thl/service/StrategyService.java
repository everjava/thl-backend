package com.everson.thl.service;

import com.everson.thl.domain.Strategy;
import com.everson.thl.repository.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    public List<Strategy> findAll() {
        return strategyRepository.findAll();
    }

    public Strategy save(Strategy strategy) {
        return strategyRepository.save(strategy);
    }

    public Strategy findById(String id) {
        //TODO validation when not found
        return strategyRepository.findById(id).get();
    }

    public void delete(String id) {
        strategyRepository.deleteById(id);
    }




}
