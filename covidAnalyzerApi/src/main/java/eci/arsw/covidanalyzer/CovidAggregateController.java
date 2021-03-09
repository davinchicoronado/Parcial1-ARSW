package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/covid/result")
public class CovidAggregateController {
    
    
    @Autowired
    @Qualifier("CovidAggregateService")
    ICovidAggregateService covidAggregateService;
    Object lock = new Object();
    

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/true-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addTruePositiveResult(@RequestBody Result result) {
        
            covidAggregateService.aggregateResult(result, ResultType.TRUE_POSITIVE);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        
    }
    @RequestMapping(value = "/false-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addFalsePositiveResult(@RequestBody Result result){ 

   
            covidAggregateService.aggregateResult(result, ResultType.FALSE_POSITIVE);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

            
        
    
    }
    
    @RequestMapping(value = "/true-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addTrueNegativeResult(@RequestBody Result result){ 

            covidAggregateService.aggregateResult(result, ResultType.TRUE_NEGATIVE);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
            
            
        
   
    }
    
    
    @RequestMapping(value = "/false-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addFalseNegative(@RequestBody Result result){ 
            covidAggregateService.aggregateResult(result, ResultType.FALSE_NEGATIVE);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
            
        }


    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/true-positive", method = RequestMethod.GET)
    public ResponseEntity<?>getTruePositiveResult() {
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_POSITIVE),HttpStatus.ACCEPTED);
    }
    
    
    
    @RequestMapping(value = "/false-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getFalsePositiveResult() {
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.FALSE_NEGATIVE),HttpStatus.ACCEPTED);
    }
    
    
    @RequestMapping(value = "/true-negative", method = RequestMethod.GET)
    public ResponseEntity getTrueNegative() {
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.TRUE_NEGATIVE),HttpStatus.ACCEPTED);
    }   
    
    
    
    @RequestMapping(value = "/false-negative", method = RequestMethod.GET)
    public ResponseEntity getFalseNegative() {
        
        return new ResponseEntity<>(covidAggregateService.getResult(ResultType.FALSE_NEGATIVE),HttpStatus.ACCEPTED);
    } 

    //TODO: Implemente el método.

    
    
    @RequestMapping(value = "/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity savePersonaWithMultipleTests(@PathVariable("id") String id) {
        synchronized(lock){
        covidAggregateService.upsertPersonWithMultipleTests(UUID.fromString(id),ResultType.TRUE_POSITIVE);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}