package com.example.chetanpandey.questionapp;

import android.support.v7.app.AppCompatActivity;
    import android.content.Context;
    import android.content.Intent;
    import android.database.sqlite.SQLiteDatabase;
    import android.os.Bundle;
    import android.text.TextUtils;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;


    public class Main2Activity extends AppCompatActivity {

    EditText ques,op1,op2,op3,op4,ans ;
    Button Submit;
    SQLiteDatabase SQLITEDATABASE;
    String Question,Option1,Option2,Option3,Option4,Ans;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ques = (EditText)findViewById(R.id.question);
        op1= (EditText)findViewById(R.id.op1);
        op2 = (EditText)findViewById(R.id.op2);
        op3 = (EditText)findViewById(R.id.op3);
        op4 = (EditText)findViewById(R.id.op4);
        ans = (EditText)findViewById(R.id.ans);

        Submit = (Button)findViewById(R.id.add);

        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                DBCreate();

                SubmitData2SQLiteDB();

            }
        });


    }

    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, question TEXT, option1 TEXT, option2 TEXT, option3 TEXT, option4 TEXT, answer TEXT);");

    }

    public void SubmitData2SQLiteDB(){

        Question= ques.getText().toString();

        Option1 = op1.getText().toString();
        Option2 = op2.getText().toString();
        Option3 = op3.getText().toString();
        Option4 = op4.getText().toString();
        Ans = ans.getText().toString();



        CheckEditTextIsEmptyOrNot( Question,Option1, Option2,Option3,Option4,Ans);

        if(CheckEditTextEmpty == true)
        {

            SQLiteQuery = "INSERT INTO demoTable (question, option1, option2 , option3 , option4 , answer) VALUES('"+Question+"', '"+Option1+"', '"+Option2+"', '"+Option3+"', '"+Option4+"', '"+Ans+"');";

            SQLITEDATABASE.execSQL(SQLiteQuery);

            Toast.makeText(Main2Activity.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();

            ClearEditTextAfterDoneTask();

        }
        else {

            Toast.makeText(Main2Activity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
        }
    }

    public void CheckEditTextIsEmptyOrNot(String Question,String Option1, String Option2, String Option3, String Option4, String Ans){

        if(TextUtils.isEmpty(Question) || TextUtils.isEmpty(Option1) || TextUtils.isEmpty(Option2) || TextUtils.isEmpty(Option3) || TextUtils.isEmpty(Option4) || TextUtils.isEmpty(Ans)){

            CheckEditTextEmpty = false ;

        }
        else {
            CheckEditTextEmpty = true ;
        }
    }

    public void ClearEditTextAfterDoneTask(){

        ques.getText().clear();
        op1.getText().clear();
        op2.getText().clear();
        op3.getText().clear();
        op4.getText().clear();
        ans.getText().clear();


    }

}


