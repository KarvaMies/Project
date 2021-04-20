package com.example.harkka_login;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileIO {
    private String fileName;
    ArrayList<Double> weightArray = new ArrayList<>();
    private String row;

    private InputStream ins;
    private BufferedReader csvReader;
    private OutputStreamWriter csvWriter;

    public ArrayList<Double> readFile(String inputName, Context context) throws IOException {
        fileName = inputName;
        {
            try {
                ins = context.openFileInput(fileName + ".csv");
                csvReader = new BufferedReader(new InputStreamReader(ins));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }   //open file
        csvReader.readLine();           //skip header line
        while ((row = csvReader.readLine()) != null){
            String[] infoPerRow = row.split(",");
            weightArray.add(Double.valueOf(infoPerRow[1]));
        }
        csvReader.close();
        System.out.println("Reading done");
        return weightArray;
    }

    public void writeFile(String inputName, ArrayList<Double> inputArray, Context context) throws IOException {
        fileName = inputName;
        String writeRow;
        {
            try {
                csvWriter = new OutputStreamWriter(context.openFileOutput(fileName + ".csv", Context.MODE_PRIVATE));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //open file
        csvWriter.write("Irrelevant,weightDouble,Irrelevant\n"); //header line
        for (double input : inputArray){
            writeRow = "0," + input + ",3\n";
            csvWriter.append(writeRow);
        }
        csvWriter.close();
        System.out.println("Writing done");

    }

    public void appendFile(String inputName, ArrayList<Double> inputArray){
        fileName = inputName;
        System.out.println("Writing done");
    }
}