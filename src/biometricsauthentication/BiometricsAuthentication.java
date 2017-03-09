
package biometricsauthentication;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BiometricsAuthentication {

    public static void recordUser(String name,float thumbLen,float thumbWidth,float indxLen,float indxWidth,
            float midLen,float midWidth,float ringLen,float ringWidth,
            float pinkieLen,float pinlkieWidth){
        String fileName = "src/db.txt";
        String entry = name+":"+thumbLen+","+thumbWidth+","+indxLen+","+indxWidth+","+midLen+","+midWidth+
                ","+ringLen+","+ringWidth+","+pinkieLen+","+pinlkieWidth;
        System.out.println(entry);
        try(FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(entry);
            
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        
    }
    public static String getInputs(float thumbLen,float thumbWidth,float indxLen,float indxWidth,
            float midLen,float midWidth,float ringLen,float ringWidth,
            float pinkieLen,float pinlkieWidth){

        String fileName = "src/db.txt";
        ArrayList<String> listOfPpl = new ArrayList<>();
        Map<String, Float> success = new HashMap<String, Float>();
        
        try {
           
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()){
                listOfPpl.add(sc.next());
            }
            sc.close();
            for(int i=0;i<listOfPpl.size();i++){
                String measurements = listOfPpl.get(i);
                String[] splittedArr= new String[2];
                splittedArr = measurements.split(":");
                String[] measurementsList= new String[10];
                measurementsList = splittedArr[1].split(",");
                
                float[] errorpercentage= new float[10];
                
               float thumbLenDiff = (Math.abs(thumbLen - Float.parseFloat(measurementsList[0]))/Float.parseFloat(measurementsList[0]))*100;
               errorpercentage[0]=thumbLenDiff;
                System.out.println(errorpercentage[0]);
               float thumbWidthDiff = (Math.abs(thumbWidth - Float.parseFloat(measurementsList[1]))/Float.parseFloat(measurementsList[1]))*100;
                errorpercentage[1]=thumbWidthDiff;
                System.out.println(errorpercentage[1]);
               float indxLenDiff = (Math.abs(indxLen - Float.parseFloat(measurementsList[2]))/Float.parseFloat(measurementsList[2]))*100;
                errorpercentage[2]=indxLenDiff;
                 System.out.println(errorpercentage[2]);
               float indxWidthDiff = (Math.abs(indxWidth - Float.parseFloat(measurementsList[3]))/Float.parseFloat(measurementsList[3]))*100;
                errorpercentage[3]=indxWidthDiff;
                System.out.println(errorpercentage[3]);
               float midLenDiff = (Math.abs(midLen - Float.parseFloat(measurementsList[4]))/Float.parseFloat(measurementsList[4]))*100;
                errorpercentage[4]=midLenDiff;
                System.out.println(errorpercentage[4]);
               float midWidthDiff = (Math.abs(midWidth - Float.parseFloat(measurementsList[5]))/Float.parseFloat(measurementsList[5]))*100;
                errorpercentage[5]=midWidthDiff;
                 System.out.println(errorpercentage[5]);
               float ringLenDiff = (Math.abs(ringLen - Float.parseFloat(measurementsList[6]))/Float.parseFloat(measurementsList[6]))*100;
                errorpercentage[6]=ringLenDiff;
                 System.out.println(errorpercentage[6]);
               float ringWidthDiff = (Math.abs(ringWidth - Float.parseFloat(measurementsList[7]))/Float.parseFloat(measurementsList[7]))*100;
                errorpercentage[7]=ringWidthDiff;
                 System.out.println(errorpercentage[7]);
               float pinkLenDiff = (Math.abs(pinkieLen - Float.parseFloat(measurementsList[8]))/Float.parseFloat(measurementsList[8]))*100;
                errorpercentage[8]=pinkLenDiff;
                 System.out.println(errorpercentage[8]);
               float pinkWidthnDiff = (Math.abs(pinlkieWidth - Float.parseFloat(measurementsList[9]))/Float.parseFloat(measurementsList[9]))*100;
                errorpercentage[9]=pinkWidthnDiff;
                 System.out.println(errorpercentage[9]);
//                 System.out.println("******************************************");
               int count =0;
               float highestErrorPercentage =0;
               for(int z=0;z<10;z++){
                   if(errorpercentage[z]<10){
                       count = count+1;
                       if(errorpercentage[z]>highestErrorPercentage){
                           highestErrorPercentage=errorpercentage[z];
                       }
                    }
               }
//                System.out.println("conut========"+count);
//                System.out.println("highest er percentage========"+highestErrorPercentage);
               if(count ==10){
                   success.put(splittedArr[0], highestErrorPercentage);
               }
            }
            if(success.isEmpty()){
//                System.out.println("Empty");
                return "";
            }else{
//                System.out.println("not empty");
                HashMap.Entry<String,Float> lowest = success.entrySet().iterator().next();   
                String lowestKey= lowest.getKey();
//                System.out.println("lowest Key in " +lowestKey);
                float lowestValue=lowest.getValue();
//                System.out.println("lowest val" +lowestValue);
                for (Map.Entry<String, Float> entry : success.entrySet()) {
                    String key = entry.getKey();
                    float value = entry.getValue();
//                    System.out.println("both key " +value+" and value "+key);
                    if(lowestValue>value){
                        lowestKey = key;
                    }
                }
//                System.out.println("final lowest key " +lowestKey);
                return lowestKey;                
            } 
        } catch (IOException e) {
                e.printStackTrace();
                return "";
        }
                
    }    
   
}
