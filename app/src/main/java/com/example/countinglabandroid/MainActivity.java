package com.example.countinglabandroid;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    String fileName;
    static String t;
    TextView fileNamePrompt, fileNotFound, results;
    EditText fileNameEdit;
    Button top, top5;
    public static Context tContext;
    public static boolean doesFileExist = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tContext = MainActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileNamePrompt = (TextView) findViewById(R.id.fileNamePrompt);
        fileNotFound = (TextView) findViewById(R.id.fileNotFound);
        results = (TextView) findViewById(R.id.resulting);

        fileNameEdit = (EditText) findViewById(R.id.fileNameEdit);

        top = (Button) findViewById(R.id.top);
        top5 = (Button) findViewById(R.id.top5);

        top.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fileName = String.valueOf(fileNameEdit.getText());
                System.out.println(fileName);

                Counter counter = new Counter("commonWords.txt", fileName);

                if (doesFileExist) {

                    String[] topFiveWords = counter.getTopFiveWords();
                    int[] topFiveFrequencies = counter.getTopFiveFrequencies();

                    String set_text = "The most common word in file " + fileName + " was " + topFiveWords[0] + " with " + topFiveFrequencies[0] + " occurences.";


                    results.setText(set_text);


                    System.out.println();
                } else {
                    fileNotFound.setText("No File Exists!");
                    results.setText("");
                }
            }
        });
        top5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fileName = String.valueOf(fileNameEdit.getText());
                System.out.println(fileName);

                Counter counter = new Counter("commonWords.txt", fileName);

                if (doesFileExist) {
                    String[] topFiveWords = counter.getTopFiveWords();
                    int[] topFiveFrequencies = counter.getTopFiveFrequencies();

                    t = "The top " + topFiveWords.length + " words in text one and their frequencies are:\n";
                    printTopFive(topFiveWords, topFiveFrequencies, 0);

                    results.setText(t);

                } else {
                    fileNotFound.setText("No File Exists!");
                    results.setText("");
                }
            }
        });
    }

    private static void printTopFive(String[] topFiveWords, int[] topFiveFrequencies, int tCnt) {
        for (int i = 0; i < topFiveWords.length; ++i) {
            if (i == 0) {
                t += (i+1 + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                t += "\n";
            }

            else {
                if (topFiveFrequencies[i] == topFiveFrequencies[i-1]) {
                    tCnt++;
                    t += (i+1-tCnt + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                    t += "\n";
                } else {
                    tCnt = 0;
                    t += (i+1 + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                    t += "\n";
                }
            }
        }
    }
}