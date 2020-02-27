package com.everson.thl.controller;

import com.everson.thl.domain.Hedge;
import com.everson.thl.service.HedgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequestMapping("/api/hedge")
@RestController
public class HedgeController {

    @Autowired
    private HedgeService hedgeService;

    @GetMapping("/all")
    public Iterable<Hedge> findAll(){
        return hedgeService.findAll();
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Hedge hedge, BindingResult result) {
        try {

            hedgeService.save(hedge);

        } catch (Exception e) {
            //TODO change
            e.printStackTrace();
        }
        return new ResponseEntity<Hedge>(hedge, HttpStatus.CREATED);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<?> findHedge(@PathVariable String id) {
        Hedge hedge = null;
        try {

            hedge = hedgeService.findById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(hedge, HttpStatus.CREATED);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            hedgeService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Rolagem '" + id + "' foi deletada", HttpStatus.OK);
    }



}
