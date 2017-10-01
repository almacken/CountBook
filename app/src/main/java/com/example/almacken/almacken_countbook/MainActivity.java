package com.example.almacken.almacken_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

//Main view of app, displays existing counters and a button to add new ones
public class MainActivity extends AppCompatActivity {

    public static CounterList counterList;
    public static CounterAdapter<Counter> adapter;

    private EditText nameText;
    private EditText valueText;
    private EditText commentText;
    private ListView counterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.nameText);
        valueText = (EditText) findViewById(R.id.valueText);
        commentText = (EditText) findViewById(R.id.commentText);
        counterListView = (ListView) findViewById(R.id.counterListView);
        final Button addCounter = (Button) findViewById(R.id.addNew);

        addCounter.setOnClickListener(new View.OnClickListener() {
            //Create a new counter, them updates view
            public void onClick(View v) {
                String newName = nameText.getText().toString();
                String newValue = valueText.getText().toString();
                String newComment = commentText.getText().toString();

                if(!(newName.isEmpty() || newValue.isEmpty())) {
                    counterList.addCounter(newName, Integer.parseInt(newValue), newComment);

                    nameText.setText("");
                    valueText.setText("");
                    commentText.setText("");
                    addCounter.setText("Add Counter (" + counterList.counters.size() + ")");
                }
            }
        });
    }

    //Displays counters
    @Override
    protected void onStart(){
        super.onStart();
        counterList = new CounterList(this);
        adapter = new CounterAdapter<Counter>(this, R.layout.listed_counter, counterList.counters);
        counterListView.setAdapter(adapter);

        //Notes number of counters on the button
        Button addCounter = (Button) findViewById(R.id.addNew);
        addCounter.setText("Add Counter (" + counterList.counters.size() + ")");
    }
}
