package com.everson.thl.controller;

import com.everson.thl.domain.Strategy;
import com.everson.thl.service.MapValidationErrorService;
import com.everson.thl.service.StrategyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/strategy")
@CrossOrigin
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @ApiOperation(value = "List all Strategies" )
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Strategies found"),
            @ApiResponse(code = 404,message = "Strategies not found")
    })
    @GetMapping("/all")
    public Iterable<Strategy> findAll() {
        return strategyService.findAll();
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Strategy strategy, BindingResult result) {
        try {
            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            if(errorMap!=null) return errorMap;

            System.out.println("" + strategy.toString());
            strategyService.save(strategy);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Strategy>(strategy, HttpStatus.CREATED);
    }



    @GetMapping("/find/{id}")
    public ResponseEntity<?> findStrategy(@PathVariable String id) {
        Strategy strategy = null;
        try {
            System.out.println("id = " + id);
            strategy = strategyService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Strategy>(strategy, HttpStatus.CREATED);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            strategyService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Estratégia com ID: '" + id + "' foi deletada", HttpStatus.OK);
    }


    @ApiOperation(value = "Delete one scroll", notes = "Delete one scroll from a Strategy" )
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "scroll deleted"),
            @ApiResponse(code = 404,message = "scroll not found")
    })
    @DeleteMapping("/scroll/delete/{scrollNumber}/{idStrategy}")
    public ResponseEntity<?> delete(@PathVariable Integer scrollNumber, @PathVariable String idStrategy) {
        Strategy strategy = null;
        try {

            strategy = strategyService.findById(idStrategy);
            strategy.getScrollList().removeIf(t -> (t.getScrollNumber() == scrollNumber));

            strategyService.save(strategy);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Strategy>(strategy, HttpStatus.CREATED);
    }


}
