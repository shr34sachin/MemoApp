package com.example.unique.memoapp;


import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;



import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {



TextView ListText;
    Database db= new Database (this);



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    //Floating action Menu section
        // Create an icon
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.mipmap.plus);




        FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(icon).build();

         //Creating Floating action menu items i.e sub action buttons
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        //First button for journal
        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.ic_text);

        //Second button for pictournal
        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.ic_photo_24dp);

        //Third button for camera
        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.ic_camera);

        //Implementing the subaction buttons
        //taking to journal from action button first
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Journal.class);
                startActivityForResult(myIntent, 0);
            }
        });


        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent=new Intent(MainActivity.this,Pictournal.class);
                startActivityForResult(intent,0);
            }

        });

        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        final int CAMERA_REQUEST = 1888;
        button3.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
                startActivityForResult(intent, 1);
            }
        });




        //attach the sub buttons to the main button
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(actionButton)
                .build();


        ListText = (TextView) findViewById(R.id.textView4);
        String text="";

        ArrayList<String> arrayList = db.getAllItems();
        for (int i = 0; i < arrayList.size(); i++) {
            text=text+arrayList.get(i)+"\n";
        }
        ListText.setText(text);
    }






}




