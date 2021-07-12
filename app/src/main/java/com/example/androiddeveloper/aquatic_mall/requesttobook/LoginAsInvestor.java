

package com.example.androiddeveloper.aquatic_mall.requesttobook;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.LogIn_Activity;
import com.example.androiddeveloper.aquatic_mall.Activities.Selling_Investor;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.InvestorLoginList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.InvestorLoginnewLIsts;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorephone;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorloginnew;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.PhoneresponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.ServerResponse;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.example.androiddeveloper.aquatic_mall.fragments.LogIn_asGUest_Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 10/02/2018.
 */

public class LoginAsInvestor extends FragmentActivity  {
    LinearLayout investordata,investorask,veryfylayout;


    DtabaeHelper mydb;

    EditText investorcode,cniccode;
    Button sendcode,verifycode;
    View v;
    ImageView checkimg;
    Spinner spinner;
    LinearLayout back;
    Dialog dialog;
    ArrayList<Investorloginnew> investorlist=new ArrayList<>();
    String floorno,shopno,item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginasinvestornew);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            floorno = extras.getString("floor");
            shopno = extras.getString("shopcode");
            item = extras.getString("item");
//get the value based on the key
        }
        checkimg=findViewById(R.id.checkimg);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.loadingshoppingdialog);

        spinner = findViewById(R.id.selectproject);

        // Spinner click listener


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("The Aquatic Mall");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);









        investorcode=findViewById(R.id.code);
        cniccode=findViewById(R.id.cnic);
        sendcode=findViewById(R.id.sendcode);


        investordata=findViewById(R.id.investorlayoutdata);
        investorask=findViewById(R.id.investorask);

        back=(LinearLayout) findViewById(R.id.rback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentHome("");
            }
        });


        mydb=new DtabaeHelper(this);




        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (investorcode.getText().toString().equals("")||cniccode.getText().toString().equals("")) {
                    Toast.makeText(LoginAsInvestor.this, "Please fill the required field", Toast.LENGTH_SHORT).show();
                }
                else if(spinner.getSelectedItem().toString().equals("Select"))
                {
                    Toast.makeText(LoginAsInvestor.this, "Please select any project.", Toast.LENGTH_SHORT).show();
                }
                else   {
                    dialog.show();
                    recallmethod(investorcode.getText().toString(),cniccode.getText().toString());

                }




            }
        });



    }



    public void recallmethod(String code,String cnic)
    {
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<InvestorLoginnewLIsts> userCall =service.investorloginnew(code,cnic);

        userCall.enqueue(new Callback<InvestorLoginnewLIsts>() {
            @Override
            public void onResponse(Call<InvestorLoginnewLIsts> call, Response<InvestorLoginnewLIsts> response) {
                ArrayList<Investorloginnew> mainview = (ArrayList<Investorloginnew>) response.body().getInvestorloginnew();

                if (mainview!=null) {
                    if(mainview.get(0).getSuccess().equals("1"))
                    {
                        investorlist=mainview;
                        for(int c=0;c<investorlist.size();c++)
                        {

                            Cursor cursor = mydb.checkaquatic();
                            if (cursor.getCount() == 0) {
                                mydb.insertauatic(investorlist.get(c).getEmail().toString(),investorlist.get(c).getName().toString(),investorlist.get(c).getCode().toString(),"Investor",investorlist.get(c).getPhonenumber().toString(),investorlist.get(c).getCnic().toString(),investorlist.get(c).getSonof().toString(),investorlist.get(c).getDate().toString(),investorlist.get(c).getImg().toString(),investorlist.get(c).getProfession().toString(),investorlist.get(c).getOffice().toString(),investorlist.get(c).getAddress().toString());
//                               mydb.inserttype("Investor");
//                               mydb.insertitem(investorcode.getText().toString());
                                if(MainActivity.investorLogin.equals("drawer")) {

                                    Intent i = new Intent(LoginAsInvestor.this, MainActivity.class);

                                    startActivity(i);
                                    LoginAsInvestor.this.finish();
                                }
                                else if(MainActivity.investorLogin.equals("SellNow"))
                                {
                                    Intent i = new Intent(LoginAsInvestor.this, Selling_Investor.class);

                                    startActivity(i);
                                    LoginAsInvestor.this.finish();
                                }
                            } else {
                                mydb.updateaquatic("1",investorlist.get(c).getEmail().toString(),investorlist.get(c).getName().toString(),investorlist.get(c).getCode().toString(),"Investor",investorlist.get(c).getPhonenumber().toString(),investorlist.get(c).getCnic().toString(),investorlist.get(c).getSonof().toString(),investorlist.get(c).getDate().toString(),investorlist.get(c).getImg().toString(),investorlist.get(c).getProfession().toString(),investorlist.get(c).getOffice().toString(),investorlist.get(c).getAddress().toString());

                                    final Dialog loading;
                                    loading = new Dialog(LoginAsInvestor.this);
                                    loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                    loading.setContentView(R.layout.loadingshoppingdialog);
                                    loading.show();

                                    MainActivity.Logindtect="Investor";

                                    MainActivity.checkemail=investorlist.get(c).getEmail().toString();
                                    MainActivity.name=investorlist.get(c).getName().toString();
                                    MainActivity.phone=investorlist.get(c).getPhonenumber().toString();
                                    MainActivity.cnic=investorlist.get(c).getCnic().toString();
                                    MainActivity.investorcode=investorlist.get(c).getCode().toString();



                                    MainActivity.sonof=investorlist.get(c).getSonof().toString();
                                    MainActivity.address=investorlist.get(c).getAddress().toString();
                                    MainActivity.office=investorlist.get(c).getOffice().toString();
                                    MainActivity.dateee=investorlist.get(c).getDate().toString();
                                    MainActivity.profession=investorlist.get(c).getProfession().toString();
                                    MainActivity.img=investorlist.get(c).getImg().toString();







                                    sendnotification();



                                    ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                                    Call<MSG> userCall =service.requestobook(MainActivity.name,MainActivity.checkemail,MainActivity.phone,"","",shopno,floorno,item);

                                    userCall.enqueue(new Callback<MSG>() {
                                        @Override
                                        public void onResponse(Call<MSG> call, Response<MSG> response) {

                                            if(response.body().getSuccess()==1)
                                            {
                                                loading.dismiss();
                                                final Dialog dialog;
                                                dialog=new Dialog(LoginAsInvestor.this);
                                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                                dialog.setContentView(R.layout.requestresponsedialog);
                                                Button ok=dialog.findViewById(R.id.ok);
                                                ok.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        dialog.dismiss();
                                                        finish();
                                                    }
                                                });
                                                dialog.show();

                                            }

//                    Toast.makeText(context, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<MSG> call, Throwable t) {
                                            Toast.makeText(LoginAsInvestor.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                            Log.d("onFailure", t.toString());
                                        }
                                    });


                            }

                        }

                    }
                    else
                    {
                        dialog.dismiss();
                        Toast.makeText(LoginAsInvestor.this, "Wrong code or cnic.Please try again.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<InvestorLoginnewLIsts> call, Throwable t) {
                try {
                    Toast.makeText(LoginAsInvestor.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });
    }






    public void sendnotification()
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<MSG> call = apiService.sendnotify("https://cpecintel.com/aquatic/topseller/sendnotify.php");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                int statusCode = response.code();


            }
            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                try {
                    Toast.makeText(LoginAsInvestor.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });
       /* ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MSG> userCall =service.sendnotification("");

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {




            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(LoginAsInvestor.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });*/
    }



































}