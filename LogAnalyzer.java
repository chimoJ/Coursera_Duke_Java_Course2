
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource();
         for (String line: fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
            }
     }
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIps.contains(ipAddr)) {
               uniqueIps.add(ipAddr);
             }
        }
        return uniqueIps.size();
        }
     public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
           int status = le.getStatusCode();
           if (status > num){
            System.out.println("LogEntry whose status code is larger than "+num+" is "+ le);
            }
        }
        }
     public ArrayList<String> uniqueIPVisitOneDay(String SomeDay){
        ArrayList<String> DateRecord = new ArrayList<String>();
        for (LogEntry le : records) {
            Date date = le.getAccessTime();
            String dateStr= date.toString().substring(4,10);
            String ipAddr = le.getIpAddress();
            //System.out.println(dateStr);
            if (dateStr.equals(SomeDay)){
              if (!DateRecord.contains(ipAddr)) {
                DateRecord.add(ipAddr);
               }
            }
        }
        if (DateRecord.size()==0){System.out.println("there is no IP visited at "+SomeDay);}
        else{System.out.println("there is "+DateRecord.size()+" IP visited at "+SomeDay);}
        return DateRecord;
        }
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> StatusRecord = new ArrayList<String>();
         for (LogEntry le : records){
           int status = le.getStatusCode();
           String ipAddr = le.getIpAddress();
           if (status >= low && status <= high){
            if (!StatusRecord.contains(ipAddr)) {
                StatusRecord.add(ipAddr);
                //System.out.println(ipAddr);
               }
            }
        }
        if (StatusRecord.size()==0){System.out.println("there is no IP's status code between "+low+" and "+high);}
        else{System.out.println("there is "+StatusRecord.size()+" unique IPs' status code between "+low+" and "+high);}
        return StatusRecord.size();
        }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
