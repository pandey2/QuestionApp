package com.example.chetanpandey.questionapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    Button prev;
    SQLiteDatabase SQLITEDATABASE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBCreate();


    }

    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, question TEXT, option1 TEXT, option2 TEXT, option3 TEXT, option4 TEXT, answer TEXT);");

    }




    public void start_exam(View view) {


        String count = "SELECT count(*) FROM demoTable";
        Cursor mcursor = SQLITEDATABASE.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0)
        {
            Intent intent = new Intent(this,GetDataActivity.class);
            startActivity(intent);
        }

else
        {
            Toast.makeText(this, "Add Questions first", Toast.LENGTH_SHORT).show();
        }

    }

    public void setting(View view) {

         Intent intent = new Intent(this,Helper_desk.class);
        startActivity(intent);
    }

    public void user(View view) {
        Intent intent = new Intent(this,User_guide.class);
        startActivity(intent);
    }
}


