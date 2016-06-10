package com.example.unique.memoapp;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;


public class Journal extends MainActivity {
    TextView tvDisplayDate;
    private ArrayList<String> data;
    private ArrayAdapter<String>arrayAdapterToDo;
    private Database db=new Database(this);
    EditText editTitle;
    EditText editNote;
    Button btnSave;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journal);


        //getting current date
        tvDisplayDate = (TextView) findViewById(R.id.dateView);
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        tvDisplayDate.setText(new StringBuilder()
                .append(yy).append(" ").append("/").append(mm + 1).append("/")
                .append(dd));



                editTitle=(EditText)findViewById(R.id.editTitle);
                editNote=(EditText)findViewById(R.id.editNote);
                btnSave=(Button)findViewById(R.id.buttonOk);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle.getText().toString();
                data.add(title);
                editTitle.setText("");

                String note=editNote.getText().toString();
                data.add(note);
                editNote.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addAllItems(data,data);

                Intent intent=new Intent(Journal.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

}






