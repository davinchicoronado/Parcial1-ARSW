/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Coronado
 */
@Service
@Qualifier("CovidAggregateService")
public class CovidAggregateService implements ICovidAggregateService {
    
    private final Map<String,List<Result>> results=new HashMap<>();
    private final Map<UUID,Integer> registered= new HashMap<>();
    
    
    @Override
    public boolean aggregateResult(Result result, ResultType type) {
        
        boolean add;
 
        if(results.containsKey(type.name())){
             registered.put(result.getId(),1);
             add = results.get(type.name()).add(result);
        }
        else{
            results.put(type.name(), new ArrayList<>());
            add = results.get(type.name()).add(result);
            registered.put(result.getId(),1);
        }
        return add;
    }
    @Override
    public List<Result> getResult(ResultType type) {
       return results.get(type.name());
    }

    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
       
        registered.put(id, registered.get(id)+1);
    }
    
}
