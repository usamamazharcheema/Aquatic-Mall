package com.example.androiddeveloper.aquatic_mall.requesttobook;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 10/02/2018.
 */

public class LogIn_asGUest_Register extends FragmentActivity  {
    EditText name, password, phonenuber, email;
    Button register;
    ImageView signup_fb, gmailsignup;
    public static final int RequestSignInCode = 7;
//    CallbackManager callbackManager;
    String gmail_name, gmail_email;
    public GoogleApiClient googleApiClient;
    public FirebaseAuth firebaseAuth;
Dialog dialog;

    public String floorno=null;
    public String shopno=null;
    public String item=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.as_guest_register);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            floorno = extras.getString("floor");
            shopno = extras.getString("shopcode");
            item = extras.getString("item");
//get the value based on the key
        }
        name=(EditText)findViewById(R.id.signup_name);
        password=(EditText)findViewById(R.id.signup_password);
        phonenuber=(EditText)findViewById(R.id.signup_phone);
        email=(EditText)findViewById(R.id.signup_email);
        register=(Button) findViewById(R.id.signup_register);
        signup_fb=(ImageView) findViewById(R.id.signup_fb);
        gmailsignup=(ImageView) findViewById(R.id.signup_gm);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || phonenuber.getText().toString().equals(""))
                {
                    Toast.makeText(LogIn_asGUest_Register.this, "Please Fill Up Requiered Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    dialog=new Dialog(LogIn_asGUest_Register.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.setContentView(R.layout.loadingshoppingdialog);
                    dialog.show();
                    SignUpByServer();
                }

            }
        });

    }


    public void UserSignInMethod(){
        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(AuthIntent, RequestSignInCode);
    }

    public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
//        Toast.makeText(SignUp.this,""+ authCredential.getProvider(),Toast.LENGTH_SHORT).show();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(LogIn_asGUest_Register.this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {
                        if (AuthResultTask.isSuccessful()){
                            // Getting Current Login user details.
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            // Hiding Login in button.
                            gmailsignup.setVisibility(View.VISIBLE);
                            gmail_name=firebaseUser.getDisplayName().toString();
                            gmail_email=firebaseUser.getEmail().toString();
                            Toast.makeText(LogIn_asGUest_Register.this,gmail_name,Toast.LENGTH_SHORT).show();

                            SignUpByGmailServer();

                        }else {
                            Toast.makeText(LogIn_asGUest_Register.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void UserSignOutFunction() {
        // Sing Out the User.
        firebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback() {
                    @Override
                    public void onResult(@NonNull Result result) {

                    }

                    public void onResult(@NonNull Status status) {

                    }
                });
        // After logout setting up login button visibility to visible.
        gmailsignup.setVisibility(View.VISIBLE);
    }
    private void SignUpByServer() {
        final String emaill = email.getText().toString();
        String passwordd = password.getText().toString();
        final String phone=phonenuber.getText().toString();
        final String namee=name.getText().toString();
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<MSG> userCall = service.insertData(namee,emaill,phone,passwordd);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
//                hidepDialog();
                //onSignupSuccess();
                Log.d("onResponse", "" + response.body().getMessage());


                if(response.body().getSuccess() == 0) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    DtabaeHelper mydb=new DtabaeHelper(LogIn_asGUest_Register.this);
                    Cursor cursor = mydb.checkaquatic();
                    if (cursor.getCount() == 0) {
                        mydb.insertauatic(emaill,namee,"guestcode","Guest",phone,"","","","","","","");
//                               mydb.inserttype("Investor");
//                               mydb.insertitem(investorcode.getText().toString());
                        dialog.dismiss();
                        Toast.makeText(LogIn_asGUest_Register.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LogIn_asGUest_Register.this, MainActivity.class);
//                        i.putExtra("investorcode",investorcode.getText().toString());
                        startActivity(i);
                        finish();
                    } else {
                        mydb.updateaquatic("1",emaill,namee,"guestcode","Guest",phone,"","","","","","","");

//                               mydb.updatetype("1", "Investor");
//                               mydb.updateitems("1", investorcode.getText().toString());
                        dialog.dismiss();
                        final Dialog loading;
                        loading = new Dialog(LogIn_asGUest_Register.this);
                        loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        loading.setContentView(R.layout.loadingshoppingdialog);
                        loading.show();
                        MainActivity.Logindtect="Guest";

                        MainActivity.checkemail=emaill;
                        MainActivity.name=namee;
                        MainActivity.phone=phone;
                        MainActivity.cnic="";
                        MainActivity.investorcode="guestcode";
                        MainActivity.investorLogin="drawer";


                        String name= MainActivity.name;
                        String email= MainActivity.checkemail;
                        String phone= MainActivity.phone;
                        String shopcode=shopno;


                        sendnotification();



                        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                        Call<MSG> userCall =service.requestobook(name,email,phone,"","",shopcode,floorno,item);

                        userCall.enqueue(new Callback<MSG>() {
                            @Override
                            public void onResponse(Call<MSG> call, Response<MSG> response) {

                                if(response.body().getSuccess()==1)
                                {
                                    loading.dismiss();
                                    final Dialog dialog;
                                    dialog=new Dialog(LogIn_asGUest_Register.this);
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
                                Toast.makeText(LogIn_asGUest_Register.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                Log.d("onFailure", t.toString());
                            }
                        });

                    }
                }else {
                    Toast.makeText(LogIn_asGUest_Register.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                try {
                    Toast.makeText(LogIn_asGUest_Register.this, "You don't have internet connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void SignUpByGmailServer() {

        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<MSG> userCall = service.GmailData(gmail_name, gmail_email);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                try {
                    Log.d("onResponse", "" + response.body().getMessage());


                    if (response.body().getSuccess() == 0) {
                        //                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(LogIn_asGUest_Register.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        editor = sharedPref.edit();
//                        editor.putString("emails", gmail_email);
//                        //                    editor.putString("passwords", password);
//                        editor.commit();
//                        DtabaeHelper mydb;
//                        mydb = new DtabaeHelper(SignUp.this);
//                        mydb.updateitems("1", gmail_email);
                        //                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        Intent i = new Intent(getContext(), MainActivity.class);
//                        startActivity(i);
//                        finish();
                    } else {
                        Toast.makeText(LogIn_asGUest_Register.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                try {
                    Toast.makeText(LogIn_asGUest_Register.this, "You don't have internet connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
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

            }
        });
        /*ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MSG> userCall =service.sendnotification("");

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {




            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(LogIn_asGUest_Register.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });*/
    }
}
