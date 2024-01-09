package com.example.countinglabandroid;

import java.util.ArrayList;

public class Parallel {
    private ArrayList<String> wordList;
    private ArrayList<Integer> count;

    public Parallel(ArrayList<String> wordList, ArrayList<Integer> count){
        this.wordList = wordList;
        this.count = count;
    }

    public ArrayList<Integer> getCount() {
        return count;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }
}
