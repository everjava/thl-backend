package com.everson.thl.service;

import com.everson.thl.domain.Hedge;
import com.everson.thl.repository.HedgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HedgeService {

    @Autowired
    private HedgeRepository hedgeRepository;

    public List<Hedge> findAll() {
        return hedgeRepository.findAll();
    }

    public Hedge save(Hedge hedge) {
        return hedgeRepository.save(hedge);
    }

    public Hedge findById(String id) {
        //TODO validation when not found
        return hedgeRepository.findById(id).get();
    }

    public void delete(String id) {
        hedgeRepository.deleteById(id);
    }


}
