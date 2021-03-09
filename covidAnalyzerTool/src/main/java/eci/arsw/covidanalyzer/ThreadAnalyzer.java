/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author David Coronado
 */
public class ThreadAnalyzer extends Thread {
    
   private int x1;
   private int x2;
   List<File> resultFiles;
   private TestReader testReader;
   private MonitorController controller;
   private ResultAnalyzer resultAnalyzer;
   private AtomicInteger amountOfFilesProcessed; 
   
    
   public ThreadAnalyzer(int x1, int x2, List<File> resultFiles,ResultAnalyzer resultAnalyzer,AtomicInteger amountOfFilesProcessed,MonitorController controller){
       //System.out.println(x1+" "+x2);
       this.x1=x1;
       this.x2=x2;
       this.resultFiles=resultFiles;
       this.resultAnalyzer=resultAnalyzer;
       testReader = new TestReader();
       this.amountOfFilesProcessed=amountOfFilesProcessed;
       this.controller=controller;
   }
   
   @Override
   public void run(){
       for(int i=x1;i<=x2;i++){
           List<Result> results = testReader.readResultsFromFile(resultFiles.get(i));
           for(Result result: results){
               controller.checkPause();
               resultAnalyzer.addResult(result);
           }
           amountOfFilesProcessed.incrementAndGet();
       
       }
   
   
   }
   
   

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
   
   
    
}
