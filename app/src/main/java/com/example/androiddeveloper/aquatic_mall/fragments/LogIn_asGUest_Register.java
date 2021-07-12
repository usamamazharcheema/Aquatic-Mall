package com.example.androiddeveloper.aquatic_mall.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
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

public class LogIn_asGUest_Register  extends Fragment {
    EditText name, password, phonenuber, email;
    Button register;
    ImageView signup_fb, gmailsignup;
    public static final int RequestSignInCode = 7;
//    CallbackManager callbackManager;
    String gmail_name, gmail_email;
    public GoogleApiClient googleApiClient;
    public FirebaseAuth firebaseAuth;
Dialog dialog;

    public static LogIn_asGUest_Register newInstance(String movieTitle) {
        LogIn_asGUest_Register fragmentAction = new LogIn_asGUest_Register();
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
        View view=inflater.inflate(R.layout.as_guest_register, container, false);
        // Inflate the layout for this fragment
        name=(EditText)view.findViewById(R.id.signup_name);
        password=(EditText)view.findViewById(R.id.signup_password);
        phonenuber=(EditText)view.findViewById(R.id.signup_phone);
        email=(EditText)view.findViewById(R.id.signup_email);
        register=(Button) view.findViewById(R.id.signup_register);
        signup_fb=(ImageView) view.findViewById(R.id.signup_fb);
        gmailsignup=(ImageView) view.findViewById(R.id.signup_gm);
     /*   callbackManager = CallbackManager.Factory.create();
        firebaseAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getContext());
        AppEventsLogger.activateApp(getContext());
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity() , new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } *//* OnConnectionFailedListener *//*)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gmailsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSignInMethod();

            }
        });*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || phonenuber.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please Fill Up Requiered Fields", Toast.LENGTH_LONG).show();
                }
                else {
                    dialog=new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.setContentView(R.layout.loadingshoppingdialog);
                    dialog.show();
                    SignUpByServer();
                }

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }
    public void UserSignInMethod(){
        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(AuthIntent, RequestSignInCode);
    }
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestSignInCode){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()){
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                FirebaseUserAuth(googleSignInAccount);

            }
        }
    }*/
    public void FirebaseUserAuth(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
//        Toast.makeText(SignUp.this,""+ authCredential.getProvider(),Toast.LENGTH_SHORT).show();

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {
                        if (AuthResultTask.isSuccessful()){
                            // Getting Current Login user details.
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            // Hiding Login in button.
                            gmailsignup.setVisibility(View.VISIBLE);
                            gmail_name=firebaseUser.getDisplayName().toString();
                            gmail_email=firebaseUser.getEmail().toString();
                            Toast.makeText(getActivity(),gmail_name,Toast.LENGTH_SHORT).show();

                            SignUpByGmailServer();

                        }else {
                            Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
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

                    DtabaeHelper mydb=new DtabaeHelper(getActivity());
                    Cursor cursor = mydb.checkaquatic();
                    if (cursor.getCount() == 0) {
                        mydb.insertauatic(emaill,namee,"guestcode","Guest",phone,"","","","","","","");
//                               mydb.inserttype("Investor");
//                               mydb.insertitem(investorcode.getText().toString());
                        dialog.dismiss();
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), MainActivity.class);
//                        i.putExtra("investorcode",investorcode.getText().toString());
                        startActivity(i);
                        getActivity().finish();
                    } else {
                        mydb.updateaquatic("1",emaill,namee,"guestcode","Guest",phone,"","","","","","","");

//                               mydb.updatetype("1", "Investor");
//                               mydb.updateitems("1", investorcode.getText().toString());
                        dialog.dismiss();
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), MainActivity.class);
//                        i.putExtra("investorcode",investorcode.getText().toString());
                        startActivity(i);
                        getActivity().finish();
                    }
                }else {
                    Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), "You don't have internet connection", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), "You don't have internet connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
