

package com.example.androiddeveloper.aquatic_mall.asinvestorcheck;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.LogIn_Activity;
import com.example.androiddeveloper.aquatic_mall.Activities.Selling_Investor;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.Interfaces.SmsListener;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.Receiver.SmsReceiver;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.InvestorLoginList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorephone;
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
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 10/02/2018.
 */

public class LoginAsInvestor extends Fragment implements View.OnFocusChangeListener, View.OnKeyListener, TextWatcher {
    LinearLayout investordata, investorask, veryfylayout;
    EditText one, two, three, four, five, six, mPinHiddenEditText;
    Button yes, no;
    DtabaeHelper mydb;
    Dialog dialog;
    String verification_code;
    EditText phonumber, investorcode;
    Button sendcode, verifycode;
    View v;
    ImageView checkimg;
    private RadioGroup radiocheckGroup;
    LinearLayout back;
    ArrayList<Investorephone> investorephones = new ArrayList<>();
    ArrayList<String> investordatalist = new ArrayList<>();
    ArrayList<ServerResponse> investorlist = new ArrayList<>();
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    public static LoginAsInvestor newInstance(String movieTitle) {
        LoginAsInvestor fragmentAction = new LoginAsInvestor();
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
        return inflater.inflate(R.layout.as_invertorfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        v = view;
        recallmethod(1);
        checkimg = view.findViewById(R.id.checkimg);
        radiocheckGroup = (RadioGroup) view.findViewById(R.id.radiogroup);


        setPINListeners(view);


        investorcode = view.findViewById(R.id.code);
        sendcode = view.findViewById(R.id.sendcode);
        verifycode = view.findViewById(R.id.verifycode);
        verifycode.setEnabled(false);
        investordata = view.findViewById(R.id.investorlayoutdata);
        investorask = view.findViewById(R.id.investorask);

        back = (LinearLayout) view.findViewById(R.id.rback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backfragment = MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentHome("");
            }
        });
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);

        mydb = new DtabaeHelper(getActivity());


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                investordata.setVisibility(View.VISIBLE);
                investorask.setVisibility(View.GONE);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn_Activity.showFragment(LogIn_asGUest_Register.newInstance(""), true);
                LogIn_Activity.guestlayout.setBackgroundResource(R.drawable.featureselectedbar);
                LogIn_Activity.invetorlayout.setBackgroundResource(R.drawable.featurebar);
            }
        });
        verifycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radiocheckGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.email) {
                    dialog.show();
                    if (verification_code.equals("" + one.getText().toString() + two.getText().toString() + three.getText().toString() + four.getText().toString() + five.getText().toString() + six.getText().toString())) {
                        dialog.dismiss();


                        int insertcounter;
                        for (int c = 0; c < investorlist.size(); c++) {
                            if (investorlist.get(c).getIcodeno().toString().equals(investorcode.getText().toString())) {
                                Cursor cursor = mydb.checkaquatic();
                                if (cursor.getCount() == 0) {
                                    mydb.insertauatic(investorlist.get(c).getEmail().toString(), investorlist.get(c).getName().toString(), investorlist.get(c).getIcodeno().toString(), "Investor", investorlist.get(c).getPhonenumber().toString(), investorlist.get(c).getCnicno().toString(),"","","","","","");
//                               mydb.inserttype("Investor");
//                               mydb.insertitem(investorcode.getText().toString());
                                    if (MainActivity.investorLogin.equals("drawer")) {

                                        Intent i = new Intent(getActivity(), MainActivity.class);

                                        startActivity(i);
                                        getActivity().finish();
                                    } else if (MainActivity.investorLogin.equals("SellNow")) {
                                        Intent i = new Intent(getActivity(), Selling_Investor.class);

                                        startActivity(i);
                                        getActivity().finish();
                                    }
                                } else {
                                    mydb.updateaquatic("1", investorlist.get(c).getEmail().toString(), investorlist.get(c).getName().toString(), investorlist.get(c).getIcodeno().toString(), "Investor", investorlist.get(c).getPhonenumber().toString(), investorlist.get(c).getCnicno().toString(),"","","","","","");

//                               mydb.updatetype("1", "Investor");
//                               mydb.updateitems("1", investorcode.getText().toString());
                                    if (MainActivity.investorLogin.equals("drawer")) {

                                        Intent i = new Intent(getActivity(), MainActivity.class);

                                        startActivity(i);
                                        getActivity().finish();
                                    } else if (MainActivity.investorLogin.equals("SellNow")) {
                                        MainActivity.Logindtect = "Investor";

                                        MainActivity.checkemail = investorlist.get(c).getEmail().toString();
                                        MainActivity.name = investorlist.get(c).getName().toString();
                                        MainActivity.phone = investorlist.get(c).getPhonenumber().toString();
                                        MainActivity.cnic = investorlist.get(c).getCnicno().toString();
                                        MainActivity.investorcode = investorlist.get(c).getIcodeno().toString();
                                        MainActivity.investorLogin = "drawer";
                                        Intent i = new Intent(getActivity(), Selling_Investor.class);

                                        startActivity(i);

                                    }
                                }
                                dialog.dismiss();
                                c = investorlist.size();
                            }
                        }

                    } else {
                        Toast.makeText(getActivity(), "Your code i wrong", Toast.LENGTH_SHORT).show();
                    }
                } else if (selectedId == R.id.phone) {
                    dialog.show();
                    verify("" + one.getText().toString() + two.getText().toString() + three.getText().toString() + four.getText().toString() + five.getText().toString() + six.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "Please select any method", Toast.LENGTH_SHORT).show();
                }

            }
        });
        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (investorcode.getText().equals("")) {
                    Toast.makeText(getActivity(), "Please fill the required field", Toast.LENGTH_SHORT).show();
                } else if (investordatalist.contains(investorcode.getText().toString())) {

                    for (int c = 0; c < investorlist.size(); c++) {
                        if (investorlist.get(c).getIcodeno().toString().equals(investorcode.getText().toString())) {
                            Cursor cursor = mydb.checkaquatic();
                            if (cursor.getCount() == 0) {
                                mydb.insertauatic(investorlist.get(c).getEmail().toString(), investorlist.get(c).getName().toString(), investorlist.get(c).getIcodeno().toString(), "Investor", investorlist.get(c).getPhonenumber().toString(), investorlist.get(c).getCnicno().toString(),"","","","","","");
//                               mydb.inserttype("Investor");
//                               mydb.insertitem(investorcode.getText().toString());
                                if (MainActivity.investorLogin.equals("drawer")) {

                                    Intent i = new Intent(getActivity(), MainActivity.class);

                                    startActivity(i);
                                    getActivity().finish();
                                } else if (MainActivity.investorLogin.equals("SellNow")) {
                                    Intent i = new Intent(getActivity(), Selling_Investor.class);

                                    startActivity(i);
                                    getActivity().finish();
                                }
                            } else {
                                mydb.updateaquatic("1", investorlist.get(c).getEmail().toString(), investorlist.get(c).getName().toString(), investorlist.get(c).getIcodeno().toString(), "Investor", investorlist.get(c).getPhonenumber().toString(), investorlist.get(c).getCnicno().toString(),"","","","","","");

//                               mydb.updatetype("1", "Investor");
//                               mydb.updateitems("1", investorcode.getText().toString());
                                if (MainActivity.investorLogin.equals("drawer")) {

                                    Intent i = new Intent(getActivity(), MainActivity.class);

                                    startActivity(i);
                                    getActivity().finish();
                                } else if (MainActivity.investorLogin.equals("SellNow")) {
                                    MainActivity.Logindtect = "Investor";

                                    MainActivity.checkemail = investorlist.get(c).getEmail().toString();
                                    MainActivity.name = investorlist.get(c).getName().toString();
                                    MainActivity.phone = investorlist.get(c).getPhonenumber().toString();
                                    MainActivity.cnic = investorlist.get(c).getCnicno().toString();
                                    MainActivity.investorcode = investorlist.get(c).getIcodeno().toString();
                                    MainActivity.investorLogin = "drawer";
                                    Intent i = new Intent(getActivity(), Selling_Investor.class);

                                    startActivity(i);

                                }
                            }
                          //  dialog.dismiss();
                            c = investorlist.size();
                        }
                    }











                  /*  dialog = new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.setContentView(R.layout.loadingshoppingdialog);
                    dialog.show();
                    int selectedId = radiocheckGroup.getCheckedRadioButtonId();
                    try {
                        if (selectedId == R.id.phone) {
                            recallmethod(investorcode.getText().toString());
                        } else if (selectedId == R.id.email) {
                            veryfyemail(investorcode.getText().toString());
                        } else {
                            Toast.makeText(getActivity(), "Please Select Any Method", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Please Select Any Method", Toast.LENGTH_SHORT).show();
                    }*/

                } else {

                    Toast.makeText(getActivity(), "Your code is wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });
        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getActivity(), "Please Check Your connection", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verification_code = s;
                Toast.makeText(getActivity(), "Code has been sent", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                veryfylayout = v.findViewById(R.id.verifylayout);
                veryfylayout.setVisibility(View.VISIBLE);
                investordata.setVisibility(View.GONE);
                super.onCodeSent(s, forceResendingToken);
            }
        };
        new LongOperation().execute("");
    }


    public void recallmethod(String j) {
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<PhoneresponseList> userCall = service.codeverify(j);

        userCall.enqueue(new Callback<PhoneresponseList>() {
            @Override
            public void onResponse(Call<PhoneresponseList> call, Response<PhoneresponseList> response) {
                ArrayList<Investorephone> mainview = (ArrayList<Investorephone>) response.body().getInvestorephone();

                if (mainview != null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        investorephones.add(mainview.get(i));
                        sensms(investorephones.get(i).getPhone().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneresponseList> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    public void veryfyemail(String j) {
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<MSG> userCall = service.emaillogin(j);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                if (response.body().getSuccess() == 0) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();




                } else {

                    dialog.dismiss();
                    veryfylayout = v.findViewById(R.id.verifylayout);
                    veryfylayout.setVisibility(View.VISIBLE);
                    investordata.setVisibility(View.GONE);
                    verification_code = response.body().getSuccess().toString();

                    Toast.makeText(getActivity(), "" + response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    public void sensms(String phone) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 60, TimeUnit.SECONDS, getActivity(), mCallback);
    }

    public void signedinwithphone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    for (int c = 0; c < investorlist.size(); c++) {
                        if (investorlist.get(c).getIcodeno().toString().equals(investorcode.getText().toString())) {
                            Cursor cursor = mydb.checkaquatic();
                            if (cursor.getCount() == 0) {
                                mydb.insertauatic(investorlist.get(c).getEmail().toString(), investorlist.get(c).getName().toString(), investorlist.get(c).getIcodeno().toString(), "Investor", investorlist.get(c).getPhonenumber().toString(), investorlist.get(c).getCnicno().toString(),"","","","","","");

                                if (MainActivity.investorLogin.equals("drawer")) {

                                    Intent i = new Intent(getActivity(), MainActivity.class);

                                    startActivity(i);
                                    getActivity().finish();
                                } else if (MainActivity.investorLogin.equals("SellNow")) {
                                    Intent i = new Intent(getActivity(), Selling_Investor.class);

                                    startActivity(i);

                                }
                            } else {
                                mydb.updateaquatic("1", investorlist.get(c).getEmail().toString(), investorlist.get(c).getName().toString(), investorlist.get(c).getIcodeno().toString(), "Investor", investorlist.get(c).getPhonenumber().toString(), investorlist.get(c).getCnicno().toString(),"","","","","","");


                                if (MainActivity.investorLogin.equals("drawer")) {

                                    Intent i = new Intent(getActivity(), MainActivity.class);

                                    startActivity(i);
                                    getActivity().finish();
                                } else if (MainActivity.investorLogin.equals("SellNow")) {

                                    MainActivity.Logindtect = "Investor";

                                    MainActivity.checkemail = investorlist.get(c).getEmail().toString();
                                    MainActivity.name = investorlist.get(c).getName().toString();
                                    MainActivity.phone = investorlist.get(c).getPhonenumber().toString();
                                    MainActivity.cnic = investorlist.get(c).getCnicno().toString();
                                    MainActivity.investorcode = investorlist.get(c).getIcodeno().toString();
                                    MainActivity.investorLogin = "drawer";


                                    Intent i = new Intent(getActivity(), Selling_Investor.class);

                                    startActivity(i);

                                }
                            }
                            dialog.dismiss();
                            c = investorlist.size();
                        }
                    }


                } else {
                    Toast.makeText(getActivity(), "User signded fali", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void verify(String v) {

        if (verification_code.equals("")) {
            verifyphone(verification_code, v);
        } else {
            verifyphone(verification_code, v);
        }

    }

    public void verifyphone(String verifycode, String inputcode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifycode, inputcode);
        signedinwithphone(credential);
    }

    public void recallmethod(int j) {
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<InvestorLoginList> userCall = service.investorlogin(j);

        userCall.enqueue(new Callback<InvestorLoginList>() {
            @Override
            public void onResponse(Call<InvestorLoginList> call, Response<InvestorLoginList> response) {
                ArrayList<ServerResponse> mainview = (ArrayList<ServerResponse>) response.body().getServerResponse();

                if (mainview != null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        investorlist.add(mainview.get(i));
                        investordatalist.add(mainview.get(i).getIcodeno());
//                        Toast.makeText(getActivity(), ""+mainview.get(i).getIn_fcodeno().toString(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<InvestorLoginList> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setDefaultPinBackground(one);
        setDefaultPinBackground(two);
        setDefaultPinBackground(three);
        setDefaultPinBackground(four);
        setDefaultPinBackground(five);
        setDefaultPinBackground(six);

        if (s.length() == 0) {
            setFocusedPinBackground(one);
            one.setText("");
        } else if (s.length() == 1) {
            setFocusedPinBackground(two);
            one.setText(s.charAt(0) + "");
            two.setText("");
            three.setText("");
            four.setText("");
            five.setText("");
            six.setText("");
        } else if (s.length() == 2) {
            setFocusedPinBackground(three);
            two.setText(s.charAt(1) + "");
            three.setText("");
            four.setText("");
            five.setText("");
            six.setText("");
        } else if (s.length() == 3) {
            setFocusedPinBackground(four);
            three.setText(s.charAt(2) + "");
            four.setText("");
            five.setText("");
            six.setText("");
        } else if (s.length() == 4) {
            setFocusedPinBackground(five);
            four.setText(s.charAt(3) + "");
            five.setText("");
            six.setText("");
        } else if (s.length() == 5) {
            setFocusedPinBackground(six);
            five.setText(s.charAt(4) + "");
            six.setText("");
        } else if (s.length() == 6) {
            setDefaultPinBackground(six);
            six.setText(s.charAt(5) + "");

            hideSoftKeyboard(six);
        }
    }

    private void setFocusedPinBackground(EditText editText) {
//        setViewBackground(editText, getResources().getDrawable(R.drawable.textfield_focused_holo_light));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setDefaultPinBackground(EditText editText) {
//        setViewBackground(editText, getResources().getDrawable(R.drawable.textfield_default_holo_light));
    }


    public static void setFocus(EditText editText) {
        if (editText == null)
            return;

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }


    @SuppressWarnings("deprecation")
    public void setViewBackground(View view, Drawable background) {
        if (view == null || background == null)
            return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int id = v.getId();
        switch (id) {
            case R.id.verify1:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.verify2:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.verify3:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.verify4:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.verify5:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;

            case R.id.verify6:
                if (hasFocus) {
                    setFocus(mPinHiddenEditText);
                    showSoftKeyboard(mPinHiddenEditText);
                }
                break;
            default:
                break;
        }
    }

    public void hideSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void showSoftKeyboard(EditText editText) {
        if (editText == null)
            return;

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    private void setPINListeners(View view) {

        one = view.findViewById(R.id.verify1);
        two = view.findViewById(R.id.verify2);
        three = view.findViewById(R.id.verify3);
        four = view.findViewById(R.id.verify4);
        five = view.findViewById(R.id.verify5);
        six = view.findViewById(R.id.verify6);

        mPinHiddenEditText = view.findViewById(R.id.pin_hidden_edittext);

        mPinHiddenEditText.addTextChangedListener(this);

        one.setOnFocusChangeListener(this);
        two.setOnFocusChangeListener(this);
        three.setOnFocusChangeListener(this);
        four.setOnFocusChangeListener(this);
        five.setOnFocusChangeListener(this);
        six.setOnFocusChangeListener(this);

        one.setOnKeyListener(this);
        two.setOnKeyListener(this);
        three.setOnKeyListener(this);
        four.setOnKeyListener(this);
        five.setOnKeyListener(this);
        six.setOnKeyListener(this);
        mPinHiddenEditText.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            final int id = v.getId();
            switch (id) {
                case R.id.pin_hidden_edittext:
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (mPinHiddenEditText.getText().length() == 6)
                            six.setText("");
                        else if (mPinHiddenEditText.getText().length() == 5)
                            five.setText("");
                        else if (mPinHiddenEditText.getText().length() == 4)
                            four.setText("");
                        else if (mPinHiddenEditText.getText().length() == 3)
                            three.setText("");
                        else if (mPinHiddenEditText.getText().length() == 2)
                            two.setText("");
                        else if (mPinHiddenEditText.getText().length() == 1)
                            one.setText("");

                        if (mPinHiddenEditText.length() > 0)
                            mPinHiddenEditText.setText(mPinHiddenEditText.getText().subSequence(0, mPinHiddenEditText.length() - 1));

                        return true;
                    }

                    break;

                default:
                    return false;
            }
        }

        return false;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String mystring = params[0];
            int f = 0;


            return investorcode.getText().toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if (investordatalist.contains(result)) {
                new LongOperation().execute("MSG");

                checkimg.setVisibility(View.VISIBLE);
            } else {
                checkimg.setVisibility(View.GONE);
                new LongOperation().execute("MSG");
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}