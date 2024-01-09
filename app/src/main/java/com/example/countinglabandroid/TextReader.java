package com.example.countinglabandroid;

import java.io.*;
import java.util.*;
public class TextReader {
    private ArrayList<String> arr = new ArrayList();
    private ArrayList<String> common = new ArrayList();

    public TextReader(String text, String cword){
        try {
            File textFile = new File(text);
            Scanner textScanner = new Scanner(textFile);
            File commonFile = new File(cword);
            Scanner commonScanner = new Scanner(commonFile);
            while (commonScanner.hasNext()) {
                common.add(commonScanner.next().toLowerCase());
            }
            while (textScanner.hasNext()) {
                String word = textScanner.next().toLowerCase();
                word = word.replace(".", "");
                word = word.replace("?", "");
                word = word.replace(",", "");
                word = word.replace("!", "");
                word = word.replace("\"", "");
                if (!common.contains(word.toLowerCase())) if(!word.contains("’")) arr.add(word);
            }
        }
        catch (Exception e){System.out.println(e);}
    }

    public ArrayList<String> getArr() {
        return arr;
    }
}
