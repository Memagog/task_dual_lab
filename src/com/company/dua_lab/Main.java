package com.company.dua_lab;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        readAndOutputResult(filename);

    }

    private static void readAndOutputResult(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String[] s = new String[2];
        String line;
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            s = line.split(" ");
            for (String i : s) {
                list.add(i);
            }
        }
        br.close();
        fr.close();
        test.addAll(bestsTrip(list));
        Collections.sort(test);
        String p = "";
        for (int i = 0; i < test.size(); i++) {
            if(!test.get(i).contains("Grotty")){
                result.add(test.get(i));
            }
        }
        result.add(p);
        for(String j: test){
            if(j.contains("Grotty")){
                result.add(j);
            }
        }
        FileWriter writer = new FileWriter("output.txt");
        for(String str : result){
            writer.write(str +"\n");
        }
        writer.close();

    }


    private static ArrayList<String> bestsTrip(ArrayList<String> a) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < a.size() - 1; i++) {
            if (i == 1 || i % 3 == 1) {
                int x = TripTime.resultTime(a.get(i),a.get(i+1));
                if(x<60){
                    result.add(a.get(i-1)+" " + a.get(i) + " "+ a.get(i+1));
                }
            }

        }

        for (int i = 0; i < a.size() - 1; i++) {

            if (i == 1 || i % 3 == 1) {


                for(int j = i+3;j<a.size() - 1;){
                    boolean tr= a.get(i).equals(a.get(j));
                    boolean td = a.get(i+1).equals(a.get(j+1));
                    if(tr&&td){
                        if(a.get(i-1).equals("Grotty")){

                            result.remove(a.get(i-1)+" " + a.get(i) + " "+ a.get(i+1));
                        }
                        else if(a.get(j-1).equals("Grotty")){
                            result.remove(a.get(j-1)+" " + a.get(j) + " "+ a.get(j+1));
                        }
                    }
                    else if(td||tr){
                        if(TripTime.resultTime(a.get(j),a.get(j+1))>TripTime.resultTime(a.get(i),a.get(i+1))){
                            result.remove(a.get(j-1)+" " + a.get(j) + " "+ a.get(j+1));
                        }
                        else result.remove(a.get(i-1)+" " + a.get(i) + " "+ a.get(i+1));

                    }

                    j+=3;
                }
              }
            }

        return result;
    }
}
