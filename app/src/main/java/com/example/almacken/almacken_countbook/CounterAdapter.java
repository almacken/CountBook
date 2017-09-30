package com.example.almacken.almacken_countbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-09-29.
 */

//Adapts a counter to be displayed as a UI element
class CounterAdapter<T> extends ArrayAdapter<Counter> {
    public CounterAdapter(Context context, int listed_counter, ArrayList<Counter> counters) {
        super(context, listed_counter, counters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //The following seven lines are taken from StackExchange
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listed_counter, null);
        }

        final Counter counter = getItem(position);

        if (counter != null) {
            //Finds all needed UI elements
            TextView nameText = (TextView) v.findViewById(R.id.nameText);
            TextView valueText = (TextView) v.findViewById(R.id.valueText);
            TextView dateText = (TextView) v.findViewById(R.id.dateText);
            Button plusButton = (Button) v.findViewById(R.id.plusButton);
            Button minusButton = (Button) v.findViewById(R.id.minusButton);
            Button resetButton = (Button) v.findViewById(R.id.resetButton);
            Button viewButton = (Button) v.findViewById(R.id.viewButton);

            //Displays all relevant counter data
            nameText.setText(counter.getName());
            valueText.setText(Integer.toString(counter.getValue()));
            dateText.setText(counter.getDate().toString());

            //Adds listeners so the buttons call the counter's methods
            plusButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    counter.increment();
                    MainActivity.counterList.saveCounters();
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    counter.decrement();
                    MainActivity.counterList.saveCounters();
                }
            });

            resetButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    counter.reset();
                    MainActivity.counterList.saveCounters();
                }
            });

            //Adds link to the counter viewing view
            viewButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewCounter.class);
                    intent.putExtra("INDEX", MainActivity.counterList.counters.indexOf(counter));
                    v.getContext().startActivity(intent);
                }
            });
        }

        return v;
    }
}
