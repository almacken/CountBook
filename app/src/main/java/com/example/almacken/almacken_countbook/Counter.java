package com.example.almacken.almacken_countbook;

import java.util.Date;

/**
 * Created by Alex on 2017-09-24.
 * Models a counter that can count up, down or reset its value.
 * All fields can be set except for date, which updates when value changes.
 * Note that commands to set the value or initial value below zero are ignored
 */

class Counter {

    private String name;
    private int value;
    private int initialValue;
    private Date date;
    private String comment;

    public Counter(String name, int value, String comment){
        this.name = name;
        this.value = value;
        this.initialValue = value;
        this.comment = comment;
        date = new Date();
    }

    public void increment(){
        value++;
        date.setTime(System.currentTimeMillis());
    }

    public void decrement(){
        if(value > 0) {
            value--;
            date.setTime(System.currentTimeMillis());
        }
    }

    public void reset(){
        value = initialValue;
        date.setTime(System.currentTimeMillis());
    }

    public void setName(String name){
        this.name = name;
    }

    public void setValue(int value) {
        if(value >= 0) {
            this.value = value;
            date.setTime(System.currentTimeMillis());
        }
    }

    public void setInitialValue(int initialValue) {
        if(initialValue >= 0) {
            this.initialValue = initialValue;
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public Date getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
