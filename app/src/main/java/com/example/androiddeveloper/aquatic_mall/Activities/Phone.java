package com.example.androiddeveloper.aquatic_mall.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.R;

public class Phone extends Activity {

    private String phone_number = "03006626242";
    private Button dial, whatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.contect_us);

        dial = (Button) findViewById(R.id.dial);
        whatsapp = (Button) findViewById(R.id.whatsapp);

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + phone_number;
                i.setData(Uri.parse(p));
                startActivity(i);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String toNumber = "+92 30066 26242"; // contains spaces.
                    toNumber = toNumber.replace("+", "").replace(" ", "");
                    Intent sendIntent = new Intent("android.intent.action.MAIN");
                    sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi!");
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setPackage("com.whatsapp");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
                catch (Exception e){
                    Toast.makeText(Phone.this, "Please Install WhatsApp", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void back (View v)
    {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.lefttoright );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.lefttoright );
    }

}
