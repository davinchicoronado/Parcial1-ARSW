package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool {

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed; 
    private List<ThreadAnalyzer> hilos;
    private int  numOfThreads = 5;
    private MonitorController controller;
    

    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
        hilos = new ArrayList<>();
        controller = new MonitorController();
    }

    public void processResultData() {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        
        amountOfFilesTotal = resultFiles.size();
        
        //Create Threads
        createThreads(numOfThreads,resultFiles);
        
        for (ThreadAnalyzer h: hilos){
            h.start();
        }
        
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        
        CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
        covidAnalyzerTool.processResultData();
  
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.contains("exit"))
                break;
            
            String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
            Set<Result> positivePeople = covidAnalyzerTool.getPositivePeople();
            String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
            message = String.format(message, covidAnalyzerTool.amountOfFilesProcessed.get(), covidAnalyzerTool.amountOfFilesTotal, positivePeople.size(), affectedPeople);
            System.out.println(message);
            covidAnalyzerTool.setPuaseThreads();
        }
    }
    
    
    private void createThreads(int numOfThreads, List<File> resultFiles){
        
        if(numOfThreads>amountOfFilesTotal){
            ThreadAnalyzer hilo;
               for(int i=1;i<=amountOfFilesTotal;i++){
               hilo = new ThreadAnalyzer(i,i, resultFiles,resultAnalyzer,amountOfFilesProcessed,controller);
               hilos.add(hilo);
            }
        }
        else{
            ThreadAnalyzer hilo;
            int cont=0;
            int range=(amountOfFilesTotal/numOfThreads)-1;
            for(int i=1;i<=numOfThreads;i++){
                    hilo=new ThreadAnalyzer(cont, cont+range , resultFiles,resultAnalyzer,amountOfFilesProcessed,controller);
                    hilos.add(hilo);
                    cont+=range+1;
            }
            if(hilos.get(numOfThreads-1).getX2()!=(amountOfFilesTotal-1)){
                hilos.get(numOfThreads-1).setX2(amountOfFilesTotal-1);  
            }
           
        }
        
      
    
    
    }
     public void setPuaseThreads(){
        controller.setPause();
    }

    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }
    
    @Override
    public String toString(){
        String message = "Processed %d out of %d files.";
        message = String.format(message, amountOfFilesProcessed.get(), amountOfFilesTotal);
        return message;
    }
    
    
}

