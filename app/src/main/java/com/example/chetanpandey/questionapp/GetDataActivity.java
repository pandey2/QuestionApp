package com.example.chetanpandey.questionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.Menu;
import android.view.View;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class GetDataActivity extends AppCompatActivity {

    Button nextbutton, previousbutton;

    TextView ques;
    RadioGroup grp;
    RadioButton b1, b2, b3, b4;
    RadioButton btn;
    String ans;
    int Right_count = 0;
    int count = 0;
    int Wrong_count = 0;

    SQLiteDatabase db;

    String GetSQliteQuery;

    Cursor c;

    TextView idtextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        ques = (TextView) findViewById(R.id.ques);
        b1 = (RadioButton) findViewById(R.id.radioButton);
        b2 = (RadioButton) findViewById(R.id.radioButton2);
        b3 = (RadioButton) findViewById(R.id.radioButton3);
        b4 = (RadioButton) findViewById(R.id.radioButton4);
        grp = (RadioGroup) findViewById(R.id.grp);
        idtextview = (TextView) findViewById(R.id.textview1);

        GetSQliteQuery = "SELECT * FROM demoTable";

        db = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        c = db.rawQuery(GetSQliteQuery, null);

        c.moveToFirst();

        GetSQLiteDatabaseRecords();
        ans = c.getString(6).toString();

    }



    public void submit(View view) {
        count++;

        if (!c.isLast()) {

            c.moveToNext();
        } else {





            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Warning");

            // set dialog message
            alertDialogBuilder

                    .setMessage("This was the last question .\nyou've Attempted "+count + " questions\n Do you want to exit ??")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            Intent intent = new Intent(GetDataActivity.this,Result.class);
                            Bundle extras = new Bundle();
                            extras.putString("right_count", String.valueOf(Right_count));
                            extras.putString("wrong_count", String.valueOf(Wrong_count));
                            intent.putExtras(extras);
                            startActivity(intent);
                            GetDataActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
        GetSQLiteDatabaseRecords();
        ans = GetAnswer();

        Toast.makeText(this, "Answer submited sucessfully", Toast.LENGTH_SHORT).show();
        int selectedId = grp.getCheckedRadioButtonId();

        if (selectedId == b1.getId()) {
            String first = b1.getText().toString();
            if (first.equals(ans)) {

                Right_count++;

            } else {
                Wrong_count++;

            }
        }
        if (selectedId == b2.getId()) {

            String first = b2.getText().toString();
            if (first.equals(ans)) {

                Right_count++;

            } else {
                Wrong_count++;


            }
        }
        if (selectedId == b3.getId()) {
            String first = b3.getText().toString();
            if (first.equals(ans)) {
                Right_count++;


            } else {

                Wrong_count++;



            }


        }
        if (selectedId == b4.getId()) {
            String first = b4.getText().toString();
            if (first.equals(ans)) {
                Right_count++;

            } else {

                Wrong_count++;


            }
        }
        grp.clearCheck();
    }

    public void GetSQLiteDatabaseRecords() {

         idtextview.setText("Question:-"+c.getString(0).toString());
        ques.setText(c.getString(1).toString());

        b1.setText(c.getString(2).toString());

        b2.setText(c.getString(3).toString());

        b3.setText(c.getString(4).toString());

        b4.setText(c.getString(5).toString());


    }

    public String GetAnswer() {
        String x = c.getString(6).toString();
        return x;
    }




    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Warning");

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you really want to Exit from exam")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Intent intent = new Intent(GetDataActivity.this,Result.class);
                        startActivity(intent);
                        GetDataActivity.this.finish();                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
    public void DBCreate(){

        db = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS aptiTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, answer_value TEXT);");

    }}



