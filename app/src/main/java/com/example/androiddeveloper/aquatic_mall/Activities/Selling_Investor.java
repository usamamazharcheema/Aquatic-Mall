package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Selling_Investor extends Activity {
    EditText investor_code, unit_code;
    LinearLayout short_down,long_down,short_text,long_text,phone,home,back;
    ImageView imgshort_down,imglong_down;
    private RadioGroup radioGroup, radioGroup1;
    TextView tv,tv1;
    Button submit;
    ViewGroup layoutt;
    private  int status=-1;
    private  int lstatus=-1;
    private  int astatus;
    private  int mstatus;
    RadioButton b,b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling__investor);
        investor_code=(EditText) findViewById(R.id.investorcode);
        investor_code.setText(MainActivity.investorcode);
        investor_code.setEnabled(false);
        unit_code=(EditText) findViewById(R.id.unitcode);
        submit=(Button) findViewById(R.id.btn);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup1 = (RadioGroup) findViewById(R.id.radio1);
         b= (RadioButton) findViewById(R.id.radioMale);
        b1= (RadioButton) findViewById(R.id.radiofeMale);
        imgshort_down=(ImageView) findViewById(R.id.imgshort_down);
        imglong_down=(ImageView) findViewById(R.id.imglong_down);
        short_down=(LinearLayout) findViewById(R.id.short_down);
        long_down=(LinearLayout) findViewById(R.id.long_down);
        short_text=(LinearLayout) findViewById(R.id.short_term);
        long_text=(LinearLayout) findViewById(R.id.long_term);
//        phone=(LinearLayout) findViewById(R.id.phone22);
//        home=(LinearLayout) findViewById(R.id.home22);
        back=(LinearLayout) findViewById(R.id.ssback);
//        phone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_DIAL);
//                String p = "tel:" + "03136617226";
//                i.setData(Uri.parse(p));
//                startActivity(i);
//                overridePendingTransition( R.anim.layoutup, R.anim.backpressedlayout );
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup.getCheckedRadioButtonId();
                if (b.isSelected()) {
                    b.setSelected(false);

                    radioGroup1.clearCheck();
                    radioGroup.clearChildFocus(b1);
                } else {
                    b.setSelected(true);
                    radioGroup1.clearCheck();
                    radioGroup1.clearChildFocus(b1);
//                    investor_code.setEnabled(true);
                        unit_code.setEnabled(true);
//                    b1.setChecked(false);

                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected = radioGroup1.getCheckedRadioButtonId();

                if (b1.isSelected()) {
                    b1.setSelected(false);
                    radioGroup.clearCheck();
                    radioGroup.clearChildFocus(b);
                }
                else {
                   b1.setSelected(true);
//                   b.setChecked(false);
                    radioGroup.clearCheck();
                    radioGroup.clearChildFocus(b);
//                    investor_code.setEnabled(true);
                    unit_code.setEnabled(true);
                }
            }
        });
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch(checkedId){
//                    case R.id.radioMale:
////                        radioGroup1.setOnCheckedChangeListener(null);
//                        b.setChecked(true);
//                        radioGroup1.clearCheck();
////                        b1.setChecked(false);
////                        b1.clearFocus();
//                        investor_code.setEnabled(true);
//                        unit_code.setEnabled(true);
//                        break;
//                }
//            }
//        });
//        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch(checkedId){
//                    case R.id.radiofeMale:
//                        radioGroup.setOnCheckedChangeListener(null);
//                        radioGroup.clearCheck();
//                        b1.setChecked(true);
//                        investor_code.setEnabled(true);
//                        unit_code.setEnabled(true);
//                        break;
//                }
//            }
//        });
        short_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = status * -1;
                if(status==1){
                    astatus=1;
                    ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    tv=new TextView(Selling_Investor.this);
                    tv.setLayoutParams(lparams);
                    tv.setText("For those clients, who are in favor of selling their property on urgent basis (45 days period); with the agreed upon condition that their property may be sold on the same price or profit loss.");
                    short_text.addView(tv);
                    imgshort_down.setBackgroundResource(R.drawable.selling_up);

                    if (mstatus==1){
                        long_text.removeView(tv1);
                        imglong_down.setBackgroundResource(R.drawable.selling_down);
                        lstatus=-1;
                    }

//                        imgshort_down.setBackgroundResource(R.drawable.selling_down);

                }


                if(status==-1){
                    astatus=0;
                    short_text.removeView(tv);
                    imgshort_down.setBackgroundResource(R.drawable.selling_down);

                }

//                short_down.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
            }
        });
        long_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstatus = lstatus * -1;
                if(lstatus==1){
                    mstatus=1;
                    ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    tv1=new TextView(Selling_Investor.this);
                    tv1.setLayoutParams(lparams);
                    tv1.setText("For those clients, who are in favor of selling their property with considerable profit margin; no time barrier included.");
                    long_text.addView(tv1);
                    imglong_down.setBackgroundResource(R.drawable.selling_up);
                    if (astatus==1) {
                        short_text.removeView(tv);
                        imgshort_down.setBackgroundResource(R.drawable.selling_down);
                        status=-1;
                    }

                }


                if(lstatus==-1){
                    mstatus=0;
                    long_text.removeView(tv1);
                    imglong_down.setBackgroundResource(R.drawable.selling_down);
                }
//                short_down.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(unit_code.getText().toString().equals(""))
                {
                    Toast.makeText(Selling_Investor.this, "Please Enter unit code.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginByServer();
                }

            }
        });
    }
    private void loginByServer() {

        final Dialog dialog2;
        dialog2=new Dialog(Selling_Investor.this);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog2.setContentView(R.layout.requastsellingfirst);
        dialog2.show();

        Button yes,no;
        yes=dialog2.findViewById(R.id.requestyes);
        no=dialog2.findViewById(R.id.requestno);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


dialog2.dismiss();
        final Dialog dialog1;
        dialog1=new Dialog(Selling_Investor.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog1.setContentView(R.layout.loadingshoppingdialog);
        dialog1.show();
        final String email = investor_code.getText().toString();
        final String password = unit_code.getText().toString();
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);
        Call<MSG> userCall = service.Selling_Data(email,password);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
//                hidepDialog();
                //onSignupSuccess();
                Log.d("onResponse", "" + response.body().getMessage());
                if(response.body().getSuccess() == 1) {
                    final Dialog dialog;
                    dialog=new Dialog(Selling_Investor.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.setContentView(R.layout.sellingrequest);
                    dialog1.dismiss();
                    dialog.show();
                    Button ok=dialog.findViewById(R.id.ok);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                           }else {
                    Toast.makeText(Selling_Investor.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(Selling_Investor.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
            }
        });
    }
}
