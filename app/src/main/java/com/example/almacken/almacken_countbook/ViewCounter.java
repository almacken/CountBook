package com.example.almacken.almacken_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//Detailed view of a single counter, allows editing of most fields
public class ViewCounter extends AppCompatActivity {

    private Counter counter;

    private TextView nameText;
    private TextView curValueText;
    private TextView iniValueText;
    private TextView commentText;

    private Button editName;
    private Button editCur;
    private Button editIni;
    private Button editComment;
    private Button deleteButton;

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counter);

        //Finds all needed UI elements
        nameText = (TextView) findViewById(R.id.nameText);
        curValueText = (TextView) findViewById(R.id.curValueText);
        iniValueText = (TextView) findViewById(R.id.iniValueText);
        commentText = (TextView) findViewById(R.id.commentText);

        editName = (Button) findViewById(R.id.editName);
        editCur = (Button) findViewById(R.id.editCur);
        editIni = (Button) findViewById(R.id.editIni);
        editComment = (Button) findViewById(R.id.editComment);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        inputText = (EditText) findViewById(R.id.inputText);

        //Finds correct counter to display
        Intent intent = getIntent();
        counter = MainActivity.counterList.counters.get(intent.getIntExtra("INDEX", -1));

        //Displays correct values in text fields and on editComment button
        nameText.setText(counter.getName());
        curValueText.setText(Integer.toString(counter.getValue()));
        iniValueText.setText(Integer.toString(counter.getInitialValue()));
        commentText.setText(counter.getComment());
        if(counter.getComment().isEmpty()){
            editComment.setText("Add Comment");
        }

        //Sets up listeners and fields for the edit buttons
        editName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userInput = inputText.getText().toString();
                if(!userInput.isEmpty()){
                    counter.setName(userInput);
                    nameText.setText(userInput);
                    MainActivity.counterList.saveCounters();
                }
            }
        });

        editCur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userInput = inputText.getText().toString();
                try{
                    int userInt = Integer.parseInt(userInput);
                    counter.setValue(userInt);
                    curValueText.setText(userInput);
                    MainActivity.counterList.saveCounters();
                } catch(NumberFormatException e){
                    inputText.setText("Not a Number!");
                }
            }
        });

        editIni.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userInput = inputText.getText().toString();
                try{
                    int userInt = Integer.parseInt(userInput);
                    counter.setInitialValue(userInt);
                    iniValueText.setText(userInput);
                    MainActivity.counterList.saveCounters();
                } catch(NumberFormatException e){
                    inputText.setText("Not a Number!");
                }
            }
        });

        editComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userInput = inputText.getText().toString();
                counter.setComment(userInput);
                commentText.setText(userInput);
                MainActivity.counterList.saveCounters();
                if(userInput.isEmpty()){
                    editComment.setText("Add Comment");
                } else {
                    editComment.setText("Edit");
                }
            }
        });

        //Deletes counter
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.counterList.deleteCounter(counter);
                MainActivity.counterList.saveCounters();
                Intent intent = new Intent(ViewCounter.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
