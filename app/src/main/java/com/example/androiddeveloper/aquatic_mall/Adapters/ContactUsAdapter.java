


package com.example.androiddeveloper.aquatic_mall.Adapters;

        import android.content.ActivityNotFoundException;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.DashPathEffect;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.PagerAdapter;
        import android.text.SpannableString;
        import android.text.style.UnderlineSpan;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.example.androiddeveloper.aquatic_mall.Activities.MyAxisValueFormatter;
        import com.example.androiddeveloper.aquatic_mall.Activities.MyValueFormatter;
        import com.example.androiddeveloper.aquatic_mall.Activities.MyXAxisValueFormatter;
        import com.example.androiddeveloper.aquatic_mall.R;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Trendingresponse;
        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.XAxis;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.formatter.IAxisValueFormatter;
        import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
        import com.github.mikephil.charting.utils.Utils;

        import java.util.ArrayList;
        import java.util.Collections;

/**
 * Created by ANDROID DEVELOPER on 22/02/2018.
 */










public class ContactUsAdapter extends PagerAdapter {

    private Context mContext;

    public ContactUsAdapter(Context context) {
        mContext = context;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.aquatic_mall, collection, false);
        final LinearLayout email,phone,facebook,web,location;
        email=layout.findViewById(R.id.emaillayout);
        phone=layout.findViewById(R.id.phonelayout);
        web=layout.findViewById(R.id.weblayout);
        facebook=layout.findViewById(R.id.facebooklayout);
       location=layout.findViewById(R.id.locationlayout);
TextView emailtxt,phonetxt,facebooktxt,webtxt,locationtxt,title,location2;
title=layout.findViewById(R.id.contacustitle);
        emailtxt=layout.findViewById(R.id.aquatic_emailtv);
        phonetxt=layout.findViewById(R.id.aquatic_phonetv);
        webtxt=layout.findViewById(R.id.aquatic_websitetv);
        facebooktxt=layout.findViewById(R.id.aquatic_facebooktv);
        locationtxt=layout.findViewById(R.id.aquatic_addresstv1);
      location2=layout.findViewById(R.id.aquatic_addresstv2);
        if(position==0)
        {
             final String email_ = mContext.getString(R.string.aquatic_email);
           final String phone_ = mContext.getString(R.string.aquatic_phone);
           final String website_ = mContext.getString(R.string.aquatic_website);
           final String facebook_ = mContext.getString(R.string.aquatic_facebook);
           final String aquatic_location = mContext.getString(R.string.aquatic_location1);
            final String aquaticlocation2 = mContext.getString(R.string.aquatic_location2);
            SpannableString content_email = new SpannableString(email_);
            content_email.setSpan(new UnderlineSpan(), 0, email_.length(), 0);
            SpannableString content_phone = new SpannableString(phone_);
            content_phone.setSpan(new UnderlineSpan(), 0,phone_.length(), 0);
            SpannableString content_website = new SpannableString( website_);
            content_website.setSpan(new UnderlineSpan(), 0, website_.length(), 0);
            SpannableString content_facebook = new SpannableString( facebook_);
            content_facebook.setSpan(new UnderlineSpan(), 0, facebook_.length(), 0);
            SpannableString content_location1 = new SpannableString( aquatic_location);
            content_location1.setSpan(new UnderlineSpan(), 0, aquatic_location.length(), 0);
            SpannableString content_location2 = new SpannableString( aquaticlocation2);
            content_location2.setSpan(new UnderlineSpan(), 0, aquaticlocation2.length(), 0);
           title.setText("The Aquatic Mall");
            emailtxt.setText(content_email);
            phonetxt.setText(content_phone);
            webtxt.setText(content_website);
            facebooktxt.setText(content_facebook);
            locationtxt.setText(content_location1);
            location2.setText(content_location2);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email_});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hi! ");
                       mContext.startActivity(Intent.createChooser(intent, ""));
                    } catch (Exception e) {
                    }
                }
            });
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        String p = "tel:" + phone_;
                        i.setData(Uri.parse(p));
                        mContext.startActivity(i);
                    } catch (Exception e) {
                    }
                }
            });
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse("https://www.google.com/maps/search/t+chok/@33.5112978,73.1781858,18z/data=!3m1!4b1"));
                       mContext.startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is not installed
                        try {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/t+chok/@33.5112978,73.1781858,18z/data=!3m1!4b1"));
                            mContext.startActivity(i);
                        }
                        catch (Exception e1)
                        {

                        }

                    }
                }
            });
           web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse(website_));
                        mContext.startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is not installed
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(website_));
                       mContext. startActivity(i);
                    }
                }
            });
            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String YourPageURL = facebook_;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                        mContext.startActivity(browserIntent);
                    } catch (Exception e) {

                    }
                }
            });
        }
        else if(position==1)
        {
            final String email_ = mContext.getString(R.string.bizventure_email);
            final String phone_ = mContext.getString(R.string.bizventure_phone);
            final String website_ = mContext.getString(R.string.bizventure_website);
            final String facebook_ = mContext.getString(R.string.bizventure_facebook);
            final String aquatic_location = mContext.getString(R.string.bizventure_location1);
            final String aquaticlocation2 = mContext.getString(R.string.bizventure_location2);
            SpannableString content_email = new SpannableString(email_);
            content_email.setSpan(new UnderlineSpan(), 0, email_.length(), 0);
            SpannableString content_phone = new SpannableString(phone_);
            content_phone.setSpan(new UnderlineSpan(), 0,phone_.length(), 0);
            SpannableString content_website = new SpannableString( website_);
            content_website.setSpan(new UnderlineSpan(), 0, website_.length(), 0);
            SpannableString content_facebook = new SpannableString( facebook_);
            content_facebook.setSpan(new UnderlineSpan(), 0, facebook_.length(), 0);
            SpannableString content_location1 = new SpannableString( aquatic_location);
            content_location1.setSpan(new UnderlineSpan(), 0, aquatic_location.length(), 0);
            SpannableString content_location2 = new SpannableString( aquaticlocation2);
            content_location2.setSpan(new UnderlineSpan(), 0, aquaticlocation2.length(), 0);
            title.setText("Bizventure Marketing");
            emailtxt.setText(content_email);
            phonetxt.setText(content_phone);
            webtxt.setText(content_website);
            facebooktxt.setText(content_facebook);
            locationtxt.setText(content_location1);
            location2.setText(content_location2);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email_});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hi! ");
                        mContext.startActivity(Intent.createChooser(intent, ""));
                    } catch (Exception e) {
                    }
                }
            });
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        String p = "tel:" + phone_;
                        i.setData(Uri.parse(p));
                        mContext.startActivity(i);
                    } catch (Exception e) {
                    }
                }
            });
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse("https://www.google.com/maps/search/t+chok/@33.5112978,73.1781858,18z/data=!3m1!4b1"));
                        mContext.startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        try {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/t+chok/@33.5112978,73.1781858,18z/data=!3m1!4b1"));
                            mContext.startActivity(i);
                        }
                        catch (Exception e1)
                        {

                        }
                    }
                }
            });
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse(website_));
                        mContext.startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is not installed
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(website_));
                        mContext. startActivity(i);
                    }
                }
            });
            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String YourPageURL = facebook_;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                        mContext.startActivity(browserIntent);
                    } catch (Exception e) {

                    }
                }
            });
        }
        else if(position==2)
        {
          final String email_ =mContext.getString(R.string.greenearth_email);
            SpannableString content_email = new SpannableString(email_);
            content_email.setSpan(new UnderlineSpan(), 0, email_.length(), 0);
           final String phone_ = mContext.getString(R.string.greenearth_phone);
            SpannableString content_phone = new SpannableString(phone_);
            content_phone.setSpan(new UnderlineSpan(), 0,phone_.length(), 0);
            final String website_ = mContext.getString(R.string.greenearth_website);
            SpannableString content_website = new SpannableString( website_);
            content_website.setSpan(new UnderlineSpan(), 0, website_.length(), 0);


           final  String facebook_ = mContext.getString(R.string.greenearth_facebook);

            SpannableString content_facebook = new SpannableString( facebook_);
            content_facebook.setSpan(new UnderlineSpan(), 0, facebook_.length(), 0);
            final String aquatic_location = mContext.getString(R.string.greenearth_location1);

            SpannableString content_location1 = new SpannableString( aquatic_location);
            content_location1.setSpan(new UnderlineSpan(), 0, aquatic_location.length(), 0);


            final String aquaticlocation2 = mContext.getString(R.string.greenearth_location2);
            SpannableString content_location2 = new SpannableString( aquaticlocation2);
            content_location2.setSpan(new UnderlineSpan(), 0, aquaticlocation2.length(), 0);







            title.setText("Green Earth Real Estate ");
            emailtxt.setText(content_email);
            phonetxt.setText(content_phone);
            webtxt.setText(content_website);
            facebooktxt.setText(content_facebook);
            locationtxt.setText(content_location1);
            location2.setText(content_location2);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email_});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "feedback");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hi! ");
                        mContext.startActivity(Intent.createChooser(intent, ""));
                    } catch (Exception e) {
                    }
                }
            });
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        String p = "tel:" + phone_;
                        i.setData(Uri.parse(p));
                        mContext.startActivity(i);
                    } catch (Exception e) {
                    }
                }
            });
            location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse("https://www.google.com/maps/search/Civic+Center,+Phase+4"));
                        mContext.startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        try {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/Civic+Center,+Phase+4"));
                            mContext.startActivity(i);
                        }
                        catch (Exception e1)
                        {

                        }
                    }
                }
            });
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(Uri.parse(website_));
                        mContext.startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is not installed
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(website_));
                        mContext. startActivity(i);
                    }
                }
            });
            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String YourPageURL = facebook_;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL));
                        mContext.startActivity(browserIntent);
                    } catch (Exception e) {

                    }
                }
            });
        }


        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return " ";
    }

}
