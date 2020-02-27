package com.everson.thl.controller;


import com.everson.thl.domain.Scroll;
import com.everson.thl.domain.Strategy;
import com.everson.thl.service.ScrollService;
import com.everson.thl.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/scroll")
public class ScrollController {

    @Autowired
    private ScrollService scrollService;

    @Autowired
    private StrategyService strategyService;

    @GetMapping("/all")
    public Iterable<Scroll> findAll() {
        return scrollService.findAll();
    }

    private static int temp = 0;

    @PostMapping("/save/{idStrategy}")
    public ResponseEntity<?> save(@Valid @RequestBody Scroll scroll, @PathVariable String idStrategy, BindingResult result) {
        try {
            System.out.println("save scroll idstrategy = " + idStrategy);
            Strategy strategy = strategyService.findById(idStrategy);

            if (strategy.getScrollList() == null || strategy.getScrollList().isEmpty()) {
                List<Scroll> scrollList = new ArrayList<>();
                scroll.setScrollNumber(1);
                scrollList.add(scroll);
                strategy.setScrollList(scrollList);
            } else {
                scroll.setScrollNumber(strategy.getScrollList().size() + 1);
                strategy.getScrollList().add(scroll);
            }

            strategyService.save(strategy);

        } catch (Exception e) {
            //TODO change
            e.printStackTrace();
        }
        return new ResponseEntity<Scroll>(scroll, HttpStatus.CREATED);
    }


    @GetMapping("/find/{scrollNumber}/{idStrategy}")
    public ResponseEntity<?> findScroll(@PathVariable Integer scrollNumber, @PathVariable String idStrategy) {
        Scroll scroll = null;
        try {

            Strategy strategy = strategyService.findById(idStrategy);
            scroll = strategy.getScrollList().
                    stream().filter(t -> t.getScrollNumber().equals(scrollNumber)).
                    findFirst().orElseThrow(NoSuchFieldError::new);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Rolagem  '" + idStrategy + "' nao encontrada", HttpStatus.OK);
        }
        return new ResponseEntity<>(scroll, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{scrollNumber}/{idStrategy}")
    public ResponseEntity<?> delete(@PathVariable Integer scrollNumber, @PathVariable String idStrategy) {
        try {
            scrollService.deleteByScrollNumber(scrollNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Rolagem '" + scrollNumber + "' foi deletada", HttpStatus.OK);
    }


    @PostMapping("/update/{idStrategy}")
    public ResponseEntity<?> update(@Valid @RequestBody Scroll updatedScroll, @PathVariable String idStrategy, BindingResult result) {
        try {
            Strategy strategy = strategyService.findById(idStrategy);
            strategy.getScrollList().removeIf(oldScroll ->
                    oldScroll.getScrollNumber().equals(updatedScroll.getScrollNumber()));
            strategy.getScrollList().add(updatedScroll);
            strategyService.save(strategy);
        } catch (Exception e) {
            //TODO change
            e.printStackTrace();
        }
        return new ResponseEntity<Scroll>(updatedScroll, HttpStatus.CREATED);
    }

}
