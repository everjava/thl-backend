package com.everson.thl.service;


import com.everson.thl.domain.Scroll;
import com.everson.thl.repository.ScrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScrollService {

    @Autowired
    private ScrollRepository scrollRepository;

    public List<Scroll> findAll() {
        return scrollRepository.findAll();
    }

    @Transactional
    public Scroll save(Scroll scroll) {
        return scrollRepository.save(scroll);
    }

    public Scroll findById(String id) {
        //TODO validation when not found
        return scrollRepository.findById(id).get();
    }

    public void delete(String id) {
        scrollRepository.deleteById(id);
    }

    public void deleteByScrollNumber(Integer scrollNumber){
        scrollRepository.deleteByScrollNumber(scrollNumber);
    }


}
