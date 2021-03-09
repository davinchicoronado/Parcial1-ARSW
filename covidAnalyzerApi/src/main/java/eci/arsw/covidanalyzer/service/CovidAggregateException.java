/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.covidanalyzer.service;

/**
 *
 * @author David Coronado
 */
public class CovidAggregateException  extends Exception {
    
    public CovidAggregateException(String message){
        super(message);
    }
    
    public CovidAggregateException(String message, Throwable cause) {
        super(message,cause);
    
    }
}
