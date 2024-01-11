package com.example.countinglabandroid;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        top.setOnClickListener(v -> {
            fileName = String.valueOf(fileNameEdit.getText());
            System.out.println(fileName);

            if (doesFileExist) {
                topFive topFive = new topFive();
                String[] topFiveWords = com.example.countinglabandroid.topFive.topFiveWords(fileName, "assets/commonWords.txt");
                int[] topFiveFrequencies = com.example.countinglabandroid.topFive.topFiveWordsFrequency(fileName, "assets/commonWords.txt");
                String set_text = "The most common word in file " + fileName + " was " + topFiveWords[0] + " with " + topFiveFrequencies[0] + " occurences.";
                results.setText(set_text);


                System.out.println();
            } else {
                fileNotFound.setText("No File Exists!");
                results.setText("");
            }
        });
        top5.setOnClickListener(v -> {
            fileName = String.valueOf(fileNameEdit.getText());
            System.out.println(fileName);
            topFive topFive = new topFive();
            String[] topFiveWords = com.example.countinglabandroid.topFive.topFiveWords(fileName, "assets/commonWords.txt");
            int[] topFiveFrequencies = com.example.countinglabandroid.topFive.topFiveWordsFrequency(fileName, "assets/commonWords.txt");
            t = "The top " + topFiveWords.length + " words in text one and their frequencies are:\n";
            printTopFive(topFiveWords, topFiveFrequencies, 0);

            results.setText(t);


        });
    }

    private static void printTopFive(String[] topFiveWords, int[] topFiveFrequencies, int count) {
        for (int i = 0; i < topFiveWords.length; ++i) {
            if (i == 0) {
                t += (i+1 + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                t += "\n";
            }

            else {
                if (topFiveFrequencies[i] == topFiveFrequencies[i-1]) {
                    count++;
                    t += (i+1-count + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                    t += "\n";
                } else {
                    count = 0;
                    t += (i+1 + ") " + topFiveWords[i] + ": " + topFiveFrequencies[i]);
                    t += "\n";
                }
            }
        }
    }
}