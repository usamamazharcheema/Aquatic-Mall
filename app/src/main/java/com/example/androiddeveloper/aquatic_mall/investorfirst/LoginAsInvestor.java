

package com.example.androiddeveloper.aquatic_mall.investorfirst;

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
import com.example.androiddeveloper.aquatic_mall.fragments.Drawer;
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

public class LoginAsInvestor extends FragmentActivity {
    LinearLayout investordata,investorask,veryfylayout;
int lenghtofcnic;

    DtabaeHelper mydb;
String cniclenght=null;
    EditText investorcode,cniccode;
    Button sendcode,verifycode;
    View v;
    ImageView checkimg;
    Spinner spinner;
    LinearLayout back;
    Dialog dialog;
    ArrayList<Investorloginnew> investorlist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginasinvestornew);

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
   //     new LongOperation().execute("");

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

//                               mydb.updatetype("1", "Investor");
//                               mydb.updateitems("1", investorcode.getText().toString());
                                if(MainActivity.investorLogin.equals("drawer")) {

                                    Intent i = new Intent(LoginAsInvestor.this, MainActivity.class);

                                    startActivity(i);
                                    LoginAsInvestor.this.finish();
                                }
                                else if(MainActivity.investorLogin.equals("SellNow"))
                                {
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








                                    dialog.dismiss();

                                    MainActivity.investorLogin="drawer";
                                    Intent i = new Intent(LoginAsInvestor.this, Selling_Investor.class);

                                    startActivity(i);

                                }
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

    class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String mystring=params[0];

cniclenght=cniccode.getText().toString();
            lenghtofcnic=cniclenght.length();
            return String.valueOf(lenghtofcnic);
        }

        @Override
        protected void onPostExecute(String result) {
if(Integer.parseInt(result)==5 || Integer.parseInt(result)==13)
{
    result=cniccode.getText()+"-";

    cniccode.setText(result);
  //  Toast.makeText(LoginAsInvestor.this, ""+result, Toast.LENGTH_SHORT).show();
}
new LongOperation().execute("");
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }



    @Override
    public void onBackPressed() {
        Intent i=new Intent(LoginAsInvestor.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}