package com.example.almacken.almacken_countbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Alex on 2017-09-27.
 * Holds a list of counters and allows management of them
 */

class CounterList {
    public ArrayList<Counter> counters;
    private static final String FILENAME = "savedCounters.sav";
    private Context context;

    //Loads existing counters from file on creation
    public CounterList(Context context){
        this.context = context;
        loadCounters();
    }

    //Adds a counter with the given parameters to itself, add saves it
    public void addCounter(String name, int value, String comment){
        Counter newCounter = new Counter(name, value, comment);
        counters.add(newCounter);
        saveCounters();
    }

    //Deletes a given counter
    public void deleteCounter(Counter oldCounter){
        counters.remove(oldCounter);
        saveCounters();
    }

    //Loads counters from a file
    private void loadCounters(){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();

            counters = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    //Saves counters to file
    public void saveCounters(){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        MainActivity.adapter.notifyDataSetChanged();
    }
}
