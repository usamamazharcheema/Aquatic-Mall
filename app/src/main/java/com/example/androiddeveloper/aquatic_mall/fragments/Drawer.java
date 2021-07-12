package com.example.androiddeveloper.aquatic_mall.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.About;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 09/02/2018.
 */

public class Drawer extends Fragment implements View.OnClickListener {
    Dialog dialog;
LinearLayout contactuslayout,latestnewlayout,ratelayout,loginlayout,aboutuslayout,homedrawer,floorplans;

TextView contactustext,latestnewstext,ratetext,logintext,aboutustext,notificationtext;
    public static Drawer newInstance(String movieTitle) {
        Drawer fragmentAction = new Drawer();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.drawernew, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactuslayout=view.findViewById(R.id.conatctuslayout);
        contactustext=view.findViewById(R.id.contactustext);
contactustext.setOnClickListener(this);
        homedrawer=view.findViewById(R.id.homedrawer);
        homedrawer.setOnClickListener(this);
contactuslayout.setOnClickListener(this);
        latestnewlayout=view.findViewById(R.id.latestnews);
        latestnewstext=view.findViewById(R.id.latestnewstext);
        latestnewstext.setOnClickListener(this);
        latestnewlayout.setOnClickListener(this);
notificationtext=view.findViewById(R.id.notifytext);
        ratelayout=view.findViewById(R.id.ratelayout);
        ratetext=view.findViewById(R.id.ratetext);
        ratelayout.setOnClickListener(this);
        ratetext.setOnClickListener(this);
        loginlayout=view.findViewById(R.id.loginlayout);
        logintext=view.findViewById(R.id.logintext);
        aboutuslayout=view.findViewById(R.id.aboutuslayout);
        aboutustext=view.findViewById(R.id.aboutustext);
        floorplans=view.findViewById(R.id.flooplans);
       /* floorplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentFloorPlans("");
            }
        });*/
        if(MainActivity.Logindtect.equals("logout"))
        {
           logintext.setText("Log In");
        }
        else
        {
            logintext.setText("Log Out");
        }
        floorplans.setOnClickListener(this);
       loginlayout.setOnClickListener(this);
        logintext.setOnClickListener(this);
        aboutustext.setOnClickListener(this);
        aboutuslayout.setOnClickListener(this);
new LongOperation().execute("");
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.conatctuslayout )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentContactUS("");
        }
        else if(view.getId()==R.id.contactustext )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentContactUS("");
        }
        if(view.getId()==R.id.aboutuslayout )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentAboutHome("");
        }
        else if(view.getId()==R.id.aboutustext )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentAboutHome("");
        }
        else if(view.getId()==R.id.latestnews )
        {
            if(MainActivity.notification==0)
            {

            }
            else {
                MainActivity.backfragment = MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentInvestornoficationfirst("");
            }
        }
        else if(view.getId()==R.id.latestnewstext )
        {
            if(MainActivity.notification==0)
            {

            }
            else {
                MainActivity.backfragment = MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentInvestornoficationfirst("");
            }
        }
        else if(view.getId()==R.id.ratelayout )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentRateStructure("");
        }
        else if(view.getId()==R.id.homedrawer )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentHome("");
        }
        else if(view.getId()==R.id.flooplans )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentFloorPlans("");
        }

        else if(view.getId()==R.id.ratetext )
        {
            MainActivity.backfragment=MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentRateStructure("");
        }
        else if(view.getId()==R.id.loginlayout )
        {
            if(MainActivity.Logindtect.equals("logout")) {
                dialog=new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.loginpopupfirst);
                Button invesor=dialog.findViewById(R.id.investorcheck);
                invesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        MainActivity.backfragment = MainActivity.currentfragment;
                        MainActivity.mNavigationManager.showFragmentLogInasinvesor("");

                    }
                });
                Button guest=dialog.findViewById(R.id.guestcheck);
                guest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        final Dialog loginnext;
                        loginnext=new Dialog(getActivity());
                        loginnext.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        loginnext.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        loginnext.setContentView(R.layout.loginguestnext);
                        loginnext.show();
                        final EditText emaildialog=loginnext.findViewById(R.id.emaildialog);
                        final EditText passwordialog=loginnext.findViewById(R.id.passworddialog);
                        Button submit=loginnext.findViewById(R.id.signindialog);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                                Call<LoginResponseList> userCall =service.insertlogin(emaildialog.getText().toString(),passwordialog.getText().toString());

                                userCall.enqueue(new Callback<LoginResponseList>() {
                                    @Override
                                    public void onResponse(Call<LoginResponseList> call, Response<LoginResponseList> response) {
                                        ArrayList<LoginResponse> mainview = (ArrayList<LoginResponse>) response.body().getLoginResponse();

                                        if (mainview!=null) {
                                            for (int i = 0; i < mainview.size(); i = i + 1) {

                                                if(mainview.get(i).getSuccess().equals("1"))
                                                {
                                                  MainActivity.investorLogin="drawer";
//                                                    Toast.makeText(getActivity(), "sssss", Toast.LENGTH_SHORT).show();
                                                    DtabaeHelper mydb=new DtabaeHelper(getActivity());
                                                    Cursor cursor = mydb.checkaquatic();
                                                    if (cursor.getCount() == 0) {
                                                        mydb.insertauatic(mainview.get(i).getEmail().toString(),mainview.get(i).getName().toString(),"guestcode","Guest",mainview.get(i).getPhonenumber().toString(),"","","","","","","");
                                                        loginnext.dismiss();

                                                        Intent in = new Intent(getActivity(), MainActivity.class);

                                                        startActivity(in);
                                                        getActivity().finish();
                                                    } else {
                                                        mydb.updateaquatic("1",mainview.get(i).getEmail().toString(),mainview.get(i).getName().toString(),"guestcode","Guest",mainview.get(i).getPhonenumber().toString(),"","","","","","","");
                                                        loginnext.dismiss();

                                                        Intent in = new Intent(getActivity(), MainActivity.class);

                                                        startActivity(in);
                                                        getActivity().finish();

                                                    }

                                                }
                                                else if (mainview.get(i).getSuccess().equals("0"))
                                                {
                                                    loginnext.show();
                                                    Toast.makeText(getActivity(), "Wrong Email OR Password", Toast.LENGTH_SHORT).show();
                                                }
                                                                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResponseList> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                        Log.d("onFailure", t.toString());
                                    }
                                });

//                                Toast.makeText(getActivity(), "LogIn", Toast.LENGTH_SHORT).show();
                            }
                        });
                        TextView newsignup=loginnext.findViewById(R.id.newsignup);
                        newsignup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loginnext.dismiss();
                                                        MainActivity.backfragment = MainActivity.currentfragment;
                        MainActivity.mNavigationManager.showFragmentLogInasGustRegistor("");
                            }
                        });


                    }
                });
                dialog.show();
            }
            else
            {
                logout();

            }
        }
        else if(view.getId()==R.id.logintext )
        {
            if(MainActivity.Logindtect.equals("logout")) {
                dialog=new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.setContentView(R.layout.loginpopupfirst);
                Button invesor=dialog.findViewById(R.id.investorcheck);
                invesor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        MainActivity.backfragment = MainActivity.currentfragment;
                        MainActivity.mNavigationManager.showFragmentLogInasinvesor("");

                    }
                });
                Button guest=dialog.findViewById(R.id.guestcheck);
                guest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                        final Dialog loginnext;
                        loginnext=new Dialog(getActivity());
                        loginnext.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        loginnext.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        loginnext.setContentView(R.layout.loginguestnext);
                        loginnext.show();
                        final EditText emaildialog=loginnext.findViewById(R.id.emaildialog);
                        final EditText passwordialog=loginnext.findViewById(R.id.passworddialog);
                        Button submit=loginnext.findViewById(R.id.signindialog);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                                Call<LoginResponseList> userCall =service.insertlogin(emaildialog.getText().toString(),passwordialog.getText().toString());

                                userCall.enqueue(new Callback<LoginResponseList>() {
                                    @Override
                                    public void onResponse(Call<LoginResponseList> call, Response<LoginResponseList> response) {
                                        ArrayList<LoginResponse> mainview = (ArrayList<LoginResponse>) response.body().getLoginResponse();

                                        if (mainview!=null) {
                                            for (int i = 0; i < mainview.size(); i = i + 1) {

                                                if(mainview.get(i).getSuccess().equals("1"))
                                                {
                                                    MainActivity.investorLogin="drawer";
//                                                    Toast.makeText(getActivity(), "sssss", Toast.LENGTH_SHORT).show();
                                                    DtabaeHelper mydb=new DtabaeHelper(getActivity());
                                                    Cursor cursor = mydb.checkaquatic();
                                                    if (cursor.getCount() == 0) {
                                                        mydb.insertauatic(mainview.get(i).getEmail().toString(),mainview.get(i).getName().toString(),"guestcode","Guest",mainview.get(i).getPhonenumber().toString(),"","","","","","","");
                                                        loginnext.dismiss();

                                                        Intent in = new Intent(getActivity(), MainActivity.class);

                                                        startActivity(in);
                                                        getActivity().finish();
                                                    } else {
                                                        mydb.updateaquatic("1",mainview.get(i).getEmail().toString(),mainview.get(i).getName().toString(),"guestcode","Guest",mainview.get(i).getPhonenumber().toString(),"","","","","","","");
                                                        loginnext.dismiss();

                                                        Intent in = new Intent(getActivity(), MainActivity.class);

                                                        startActivity(in);
                                                        getActivity().finish();

                                                    }

                                                }
                                                else if (mainview.get(i).getSuccess().equals("0"))
                                                {
                                                    loginnext.show();
                                                    Toast.makeText(getActivity(), "Wrong Email OR Password", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResponseList> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                        Log.d("onFailure", t.toString());
                                    }
                                });

//                                Toast.makeText(getActivity(), "LogIn", Toast.LENGTH_SHORT).show();
                            }
                        });
                        TextView newsignup=loginnext.findViewById(R.id.newsignup);
                        newsignup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loginnext.dismiss();
                                MainActivity.backfragment = MainActivity.currentfragment;
                                MainActivity.mNavigationManager.showFragmentLogInasGustRegistor("");
                            }
                        });


                    }
                });
                dialog.show();
            }
            else
            {
                logout();


            }
        }

    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String mystring=params[0];
            int f=0;


            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (MainActivity.notification > 0) {
                    notificationtext.setVisibility(View.VISIBLE);
                    notificationtext.setText(String.valueOf(MainActivity.notification));
//               latestnewlayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
            catch (Exception e)
            {

            }
            try {
                new LongOperation().execute("MSG");
            }
            catch(Exception e)
            {

            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
    public void logout()
    {
       /* ManageResponse service = ApiClient.getClient().create( ManageResponse.class);
String key=FirebaseInstanceId.getInstance().getToken().toString();
        Call<MSG> userCall =service.logout(key);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                if(response.body().getSuccess() == 1) {
                    DtabaeHelper mydb=new DtabaeHelper(getActivity());
                    mydb.updateaquatic("1","","","","","","");
                    MainActivity.t.cancel();
                    MainActivity.drawerpicclose();
                    Intent i=new Intent(getActivity(),MainActivity.class);
                    getActivity().startActivity(i);
                    getActivity().finish();
                }
                else
                {
                    Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });*/
        DtabaeHelper mydb=new DtabaeHelper(getActivity());
        mydb.updateaquatic("1","","","","","","","","","","","","");
        MainActivity.t.cancel();
        MainActivity.drawerpicclose();
        Intent i=new Intent(getActivity(),MainActivity.class);
        getActivity().startActivity(i);
        getActivity().finish();
    }

}
