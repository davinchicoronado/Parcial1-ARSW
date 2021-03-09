
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import eci.arsw.covidanalyzer.Result;
import eci.arsw.covidanalyzer.TestReporter;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Coronado
 */
public class CovidAggregateServicesTest {
        
    
     @Test
    public void saveNewAndLoadTest(){
       
        Result resultado = new Result();
        
        resultado.setId(UUID.fromString("0a8f2a02-1dab-4d56-a611-5bd4f53c7643"));
        resultado.setFirstName("Coraline");
        resultado.setLastName("Hazzard");
        resultado.setGender("Non-binary");
        resultado.setEmail("chazzardj@surveymonkey.com");
        resultado.setResult(false);
        resultado.setTestSpecifity(0.96);
        resultado.setBirthDate("1983-01-10T06:11:33Z");
        resultado.setTestDate("2001-07-08T17:09:32Z");
        
        TestReporter.report(resultado,0);
        HttpResponse jsonResponse=null;
         try {
             jsonResponse = Unirest.get("http://localhost:8080/covid/result/true-positive").header("Content-Type", "application/json; charset=utf-8").asJson();
         } catch (UnirestException ex) {
             Logger.getLogger(CovidAggregateServicesTest.class.getName()).log(Level.SEVERE, null, ex);
         }
        assertNotNull(jsonResponse.getBody());
        assertEquals(202, jsonResponse.getStatus());
    }
    
    @Test
    public void saveNewAndLoadTest2(){
       
        Result resultado = new Result();
        
        resultado.setId(UUID.fromString("0a8f2a02-1dab-4d56-a611-5bd4f53c7643"));
        resultado.setFirstName("Coraline");
        resultado.setLastName("Hazzard");
        resultado.setGender("Non-binary");
        resultado.setEmail("chazzardj@surveymonkey.com");
        resultado.setResult(false);
        resultado.setTestSpecifity(0.96);
        resultado.setBirthDate("1983-01-10T06:11:33Z");
        resultado.setTestDate("2001-07-08T17:09:32Z");
        
        TestReporter.report(resultado,1);
        HttpResponse jsonResponse=null;
         try {
             jsonResponse = Unirest.get("http://localhost:8080/covid/result/false-positive").header("Content-Type", "application/json; charset=utf-8").asJson();
         } catch (UnirestException ex) {
             Logger.getLogger(CovidAggregateServicesTest.class.getName()).log(Level.SEVERE, null, ex);
         }
        assertNotNull(jsonResponse.getBody());
        assertEquals(202, jsonResponse.getStatus());
    }
    @Test
    public void saveNewAndLoadTest3(){
       
        Result resultado = new Result();
        
        resultado.setId(UUID.fromString("0a8f2a02-1dab-4d56-a611-5bd4f53c7643"));
        resultado.setFirstName("Coraline");
        resultado.setLastName("Hazzard");
        resultado.setGender("Non-binary");
        resultado.setEmail("chazzardj@surveymonkey.com");
        resultado.setResult(false);
        resultado.setTestSpecifity(0.96);
        resultado.setBirthDate("1983-01-10T06:11:33Z");
        resultado.setTestDate("2001-07-08T17:09:32Z");
        
        TestReporter.report(resultado,2);
        HttpResponse jsonResponse=null;
         try {
             jsonResponse = Unirest.get("http://localhost:8080/covid/result/true-negative").header("Content-Type", "application/json; charset=utf-8").asJson();
         } catch (UnirestException ex) {
             Logger.getLogger(CovidAggregateServicesTest.class.getName()).log(Level.SEVERE, null, ex);
         }
        assertNotNull(jsonResponse.getBody());
        assertEquals(202, jsonResponse.getStatus());
    }
    
    
@Test
    public void saveNewAndLoadTest4(){
       
        Result resultado = new Result();
        
        resultado.setId(UUID.fromString("0a8f2a02-1dab-4d56-a611-5bd4f53c7643"));
        resultado.setFirstName("Coraline");
        resultado.setLastName("Hazzard");
        resultado.setGender("Non-binary");
        resultado.setEmail("chazzardj@surveymonkey.com");
        resultado.setResult(false);
        resultado.setTestSpecifity(0.96);
        resultado.setBirthDate("1983-01-10T06:11:33Z");
        resultado.setTestDate("2001-07-08T17:09:32Z");
        
        TestReporter.report(resultado,2);
        HttpResponse jsonResponse=null;
         try {
             jsonResponse = Unirest.put("http://localhost:8080/covid/result/persona/0a8f2a02-1dab-4d56-a611-5bd4f53c7643").header("Content-Type", "application/json; charset=utf-8").asJson();
         } catch (UnirestException ex) {
             Logger.getLogger(CovidAggregateServicesTest.class.getName()).log(Level.SEVERE, null, ex);
         }
        assertNotNull(jsonResponse.getBody());
        System.out.println(jsonResponse.getStatus());
        assertEquals(202, jsonResponse.getStatus());
    }
    
    
}
    

