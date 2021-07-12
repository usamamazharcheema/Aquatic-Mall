package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANDROID DEVELOPER on 01/03/2018.
 */

public class NotificationActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationlayout);
       /* spinner=findViewById(R.id.spinner);
        newsimg=findViewById(R.id.noficationimg);
        textView=findViewById(R.id.newsdetails);
        List<String> categories = new ArrayList<String>();
        categories.add("    ");
        categories.add("save");
        categories.add("share");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if(item.equals("share"))
                {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notificationimg);
//                        BitmapDrawable drawable = (BitmapDrawable) newsimg.getDrawable();
//                        Bitmap bitmap = drawable.getBitmap();
                        prepareShareIntent(bitmap);
                    }
                    catch (Exception e)
                    {

                    }
                }
                else if(item.equals("save")) {
                    try
                    {
                        ByteArrayOutputStream bytearrayoutputstream;
                        File file;
                        FileOutputStream fileoutputstream;
                        bytearrayoutputstream = new ByteArrayOutputStream();
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.notificationimg);
                      *//*  BitmapDrawable drawable = (BitmapDrawable)newsimg.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();*//*
                        bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);

                        file = new File(Environment.getExternalStorageDirectory() + "/Aquatic Mall" + "/" + "Zameen_Expo");

                        try

                        {
                            file.createNewFile();

                            fileoutputstream = new FileOutputStream(file);

                            fileoutputstream.write(bytearrayoutputstream.toByteArray());

                            fileoutputstream.close();

                        } catch (Exception e)

                        {

                            e.printStackTrace();

                        }

                        Toast.makeText(NotificationActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                    }
                }
         *//*  // Showing selected spinner item
           Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();*//*

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NotificationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


   /* private void prepareShareIntent(Bitmap bmp) {
        Uri bmpUri = getLocalBitmapUri(bmp); // see previous remote images section
        // Construct share intent as described above based on bitmap

        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);

        shareIntent.putExtra(Intent.EXTRA_TEXT,textView.getText().toString()   );
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image*//*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
       startActivity(Intent.createChooser(shareIntent, "Share Opportunity"));

    }*/
    /*private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        File file = new File(NotificationActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmpUri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }*/
}
