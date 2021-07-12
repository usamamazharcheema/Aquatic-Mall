package com.example.androiddeveloper.aquatic_mall.fragments;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;


public class Bizventure extends Fragment {

    private String email = "contact@bizventure.info";
    private String phone = "051-5418233";
    private String website = "https://www.bizventure.info/";
    private String facebook = "https://www.facebook.com/bizventuremarketing/";
    private String bizventure_location = "https://www.google.com/maps/search/Civic+Center,+Phase+4";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bizventure, container, false);

        TextView bizventure_email = (TextView) view.findViewById(R.id.bizventure_emailtv);
        TextView bizventure_phone = (TextView) view.findViewById(R.id.bizventure_phonetv);
        TextView bizventure_website = (TextView) view.findViewById(R.id.bizventure_websitetv);
        TextView bizventure_facebook = (TextView) view.findViewById(R.id.bizventure_facebooktv);
        TextView address1 = (TextView) view.findViewById(R.id.bizventure_addresstv1);
        TextView address2 = (TextView) view.findViewById(R.id.bizventure_addresstv2);

        ImageView bizventure_emailimg = (ImageView) view.findViewById(R.id.bizventure_emailimg);
        ImageView bizventure_phoneimg = (ImageView) view.findViewById(R.id.bizventure_phoneimg);
        ImageView bizventure_websiteimg = (ImageView) view.findViewById(R.id.bizventure_websiteimg);
        ImageView bizventure_facebookimg = (ImageView) view.findViewById(R.id.bizventure_facebookimg);
        ImageView addressimg = (ImageView) view.findViewById(R.id.bizventure_loactionimg);

        bizventure_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email();
            }
        });

        bizventure_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone();
            }
        });

        bizventure_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                website();
            }
        });

        bizventure_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebook();
            }
        });

        address1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location();
            }
        });

        address2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location();
            }
        });

        bizventure_emailimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email();
            }
        });

        bizventure_phoneimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone();
            }
        });

        bizventure_websiteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                website();
            }
        });

        bizventure_facebookimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebook();
            }
        });

        addressimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location();
            }
        });
        return view;
    }

    public void email() {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
            intent.putExtra(Intent.EXTRA_TEXT, "Hi! ");
            startActivity(Intent.createChooser(intent, ""));
        } catch (Exception e) {
        }
    }

    public void phone() {
        try {
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + phone;
            i.setData(Uri.parse(p));
            startActivity(i);
        } catch (Exception e) {
        }
    }

    public void website() {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(website));
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
            startActivity(i);
        }
    }

    public void facebook() {
        try {
            String YourPageURL = facebook;
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
            startActivity(browserIntent);
        } catch (Exception e) {

        }
    }

    public void location() {
        try {
            Intent i = new Intent("android.intent.action.MAIN");
            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
            i.addCategory("android.intent.category.LAUNCHER");
            i.setData(Uri.parse(bizventure_location));
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            // Chrome is not installed
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(bizventure_location));
            startActivity(i);
        }
    }
}
