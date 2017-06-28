package com.example.chetanpandey.questionapp;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;


public class Helper_desk extends AppCompatActivity {
String TABLE_NAME ="demoTable";
SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_desk);

    }


    public void add(View view) {

        Intent intent = new Intent(Helper_desk.this, Main2Activity.class);
        startActivity(intent);
    }

    public void remove(View view) {


       sqLiteDatabase = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("delete from "+ TABLE_NAME);

        Toast.makeText(this, "ALL Questions Deleted Sucessfully", Toast.LENGTH_SHORT).show();
    }



}

