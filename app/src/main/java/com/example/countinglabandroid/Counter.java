package com.example.countinglabandroid;
import java.util.*;
public class Counter {
    public Parallel count(String text, String cword){
        TextReader textFile = new TextReader(text, cword);
        ArrayList<String> arr = textFile.getArr();
        ArrayList<String> duplicate = new ArrayList<String>();
        ArrayList<Integer> occurences = new ArrayList<Integer>();
        for(int i = 0; i < arr.size(); i++){ //remove duplicate
            if(!duplicate.contains(arr.get(i))){
                duplicate.add(arr.get(i));
            }
        }

        for(int i = 0; i < duplicate.size(); i++){ // count occurences
            int count = 0;
            for(int j = 0; j < arr.size(); j++){
                if(arr.get(j).equals(duplicate.get(i))) count++;
            }
            occurences.add(count);
        }
        return new Parallel(duplicate, occurences);
    }
}
