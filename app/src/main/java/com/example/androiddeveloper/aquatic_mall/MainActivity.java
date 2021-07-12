package com.example.androiddeveloper.aquatic_mall;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bizventure.androiddeveloper.aquatic_mall.Animationopen;
import com.example.androiddeveloper.aquatic_mall.Activities.HomeActivity;
import com.example.androiddeveloper.aquatic_mall.Activities.Phone;
import com.example.androiddeveloper.aquatic_mall.Activities.Selling_Investor;
import com.example.androiddeveloper.aquatic_mall.Activities.Trendiing;
import com.example.androiddeveloper.aquatic_mall.Adapters.ExpandableListAdapter;
import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.MyFireBaseInstanceIdService;
import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.Topsellerslist;
import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.Topsellesr;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.AdminResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Adminslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.example.androiddeveloper.aquatic_mall.fragments.DrawerFragment;
import com.example.androiddeveloper.aquatic_mall.fragments.FragmentNavigationManager;
import com.example.androiddeveloper.aquatic_mall.fragments.NavigationManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class MainActivity extends FragmentActivity implements View.OnTouchListener {
    public static NavigationManager mNavigationManager;
    public static String swipe = "home";
    float x1, x2;
    float y1, y2;
   public static Activity activity;
   public  static String lookingtoinvest="Home";
   public static int notification=0;
    ImageView trending;
    TextView sellnoewstext;
    public static String drawercheck = "close";
    public static ImageView drawerpic;
    public static String currentfragment = "Home";
    public static String backfragment = "Back";
    public static ImageView homeicon;
    public static String Logindtect = "logout";
    public static String usertype=null;

    DtabaeHelper mydb;
    public static CountDownTimer t;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
public  static ImageView chaticon;
    public static String checkemail = "";
    public static String name = "";
    public static String phone = "";
    public static String cnic = "";

    public static String sonof = "";
    public static String address = "";
    public static String office = "";
    public static String profession = "";
    public static String img = "";
    public static String dateee = "";
    private ViewGroup mRrootLayout;
    private int _xDelta;
    private int _yDelta;

    public static String investorcode = "";
    public static String investorLogin = "drawer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        activity = this;
        drawerpic = findViewById(R.id.drawepic);
        notification=0;
        mydb = new DtabaeHelper(this);
        Cursor c = mydb.checkaquatic();
        if (c.getCount() == 0) {

        } else {
            while (c.moveToNext()) {
                String type = c.getString(4);
                checkemail = c.getString(1);
                name = c.getString(2);
                phone = c.getString(5);
                cnic = c.getString(6);
                investorcode = c.getString(3);
             //   Toast.makeText(activity, ""+checkemail, Toast.LENGTH_SHORT).show();
                topselles(FirebaseInstanceId.getInstance().getToken());
                adminlists(FirebaseInstanceId.getInstance().getToken());
                if (type.equals("")) {
                    Logindtect = "logout";
                } else if (type.equals("Investor")) {

                    Logindtect = "Investor";
                } else if (type.equals("Guest")) {

                    Logindtect = "Guest";
                }

            }
        }
        MainActivity.mNavigationManager = FragmentNavigationManager.obtain(this);
        if (mNavigationManager != null) {
            MainActivity.mNavigationManager.showFragmentHome("");
        }
        trending = findViewById(R.id.trending);
        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.backfragment.equals("Back"))

                {

                } else {
                    MainActivity.t.cancel();

                    MainActivity.mNavigationManager.showFragmentHome("");
                }

            }
        });
        sellnoewstext = findViewById(R.id.sellnowtext);
        if (MainActivity.Logindtect.equals("Investor")) {

            sellnoewstext.setEnabled(true);
            sellnoewstext.setTextColor(Color.parseColor("#ffffff"));
        } else {
            sellnoewstext.setEnabled(false);
            sellnoewstext.setTextColor(Color.parseColor("#808285"));
        }
        homeicon = findViewById(R.id.homeicon);
        chaticon=findViewById(R.id.chaticon);

    }

    public void drawer(View v) {
        MainActivity.backfragment = MainActivity.currentfragment;
        if (MainActivity.drawercheck.equals("close")) {
            MainActivity.t.cancel();
            MainActivity.mNavigationManager.showFragmentDrawer("");
            MainActivity.drawercheck = "open";
        } else if (MainActivity.drawercheck.equals("open")) {
            MainActivity.t.cancel();
            backpressed();
        }


    }

    @Override
    public void onBackPressed() {
        drawerpic.setImageResource(R.drawable.homedrawericon);
        backpressed();

    }

    public void phone(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String p = "tel:" + "03351115551";
        i.setData(Uri.parse(p));
        startActivity(i);
        overridePendingTransition(R.anim.layoutup, R.anim.backpressedlayout);
    }

    public static void drawerpicopen() {
        drawerpic.setImageResource(R.drawable.navigationclose);
        MainActivity.drawercheck.equals("open");
    }

    public static void drawerpicclose() {
        drawerpic.setImageResource(R.drawable.homedrawericon);
        MainActivity.drawercheck="close";
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {

            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

//                                                 / /if left to right sweep event on screen
                if (x1 < x2) {
//                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if right to left sweep event on screen
                if (x1 > x2) {
//                    Toast.makeText(getActivity(), "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if UP to Down sweep event on screen
                if (y1 < y2) {
                    if (MainActivity.drawercheck.equals("open")) {
                        backpressed();
                    }
                }

//                                                 / /if Down to UP sweep event on screen
                if (y1 > y2) {
//                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }


    public static void backpressed() {
        drawerpic.setImageResource(R.drawable.homedrawericon);
        MainActivity.t.cancel();
        if (MainActivity.backfragment.equals("Home"))

        {

            MainActivity.mNavigationManager.showFragmentHome("");
            MainActivity.drawercheck = "close";
        } else if (MainActivity.backfragment.equals("Latesnews")) {
            mNavigationManager.showFragmentLatesnews("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";

        }
        else if (MainActivity.backfragment.equals("Back")) {
      activity.finish();

        }

        else if (MainActivity.backfragment.equals("AboutMall")) {
            mNavigationManager.showFragmentAboutMall("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        } else if (MainActivity.backfragment.equals("AboutMallFeatures")) {
            mNavigationManager.showFragmentAboutMallFeatures("");
            MainActivity.backfragment = "AboutMall";
            MainActivity.drawercheck = "close";
        } else if (MainActivity.backfragment.equals("Trending")) {
            mNavigationManager.showFragmentHome("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("LookingToInvest")) {
            mNavigationManager.showFragmentLookingToInvesment("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("ContactUs")) {
            mNavigationManager.showFragmentContactUS("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";

        }
        else if (MainActivity.backfragment.equals("About")) {
            mNavigationManager.showFragmentAboutHome("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("RateStructure")) {
            mNavigationManager.showFragmentRateStructure("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("LoginInvesor")) {
            mNavigationManager.showFragmentLogInasinvesor("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("LoginGuest")) {
            mNavigationManager.showFragmentLogInasGustRegistor("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";
        }

        else if (MainActivity.backfragment.equals("Exclusive")) {
            mNavigationManager.showFragmentExclusive("");
            MainActivity.backfragment = "Features";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("Features")) {
            mNavigationManager.showFragmentFeatures("");
            MainActivity.backfragment = "AboutMall";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("ExclusiveDetail")) {
            mNavigationManager.showFragmentExclusiveDetail("");
            MainActivity.backfragment = "Exclusive";
            MainActivity.drawercheck = "close";
        }
        else if (MainActivity.backfragment.equals("FloorPLans")) {
            mNavigationManager.showFragmentFloorPlans("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";

        }

        else if (MainActivity.backfragment.equals("InvestorNotificationFirst")) {
            if(notification==0)
            {
                MainActivity.mNavigationManager.showFragmentHome("");
                MainActivity.drawercheck = "close";
            }
            else {
                mNavigationManager.showFragmentInvestornoficationfirst("");
                MainActivity.backfragment = "Home";
                MainActivity.drawercheck = "close";
            }

        }
        else if (MainActivity.backfragment.equals("BookingRequestDetail")) {
            if(notification==0)
            {
                MainActivity.backfragment = "Home";
            }
            else
            {
                MainActivity.backfragment = "InvestorNotificationFirst";
            }

            mNavigationManager.showFragmentBookingRequestDetail("");

            MainActivity.drawercheck = "close";

        }
        else if (MainActivity.backfragment.equals("Admin_Guest_List")) {


            mNavigationManager.showFragmentAdmin_Guest_List("");
            MainActivity.backfragment = "Home";
            MainActivity.drawercheck = "close";

        }

        MainActivity.chaticon.setVisibility(View.VISIBLE);
        MainActivity.homeiconset(R.drawable.newicon);




    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void homeiconset(int Res) {
        homeicon.setImageResource(Res);
    }

    public void invsetnow(View v) {
if(lookingtoinvest.equals("Home")) {
    MainActivity.t.cancel();
    MainActivity.backfragment = MainActivity.currentfragment;
    MainActivity.mNavigationManager.showFragmentLookingToInvesment("");
}
else
{

}

    }

    public void sellnow(View v) {
        Intent i = new Intent(MainActivity.this, Selling_Investor.class);
        startActivity(i);

    }

    public void loginfirst() {
        final Dialog dialog;
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.loginpopupfirst);
        dialog.show();
        Button invesor = dialog.findViewById(R.id.investorcheck);
        invesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                MainActivity.backfragment = MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLogInasinvesor("");

            }
        });
        Button guest = dialog.findViewById(R.id.guestcheck);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                final Dialog loginnext;
                loginnext = new Dialog(MainActivity.this);
                loginnext.requestWindowFeature(Window.FEATURE_NO_TITLE);
                loginnext.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                loginnext.setContentView(R.layout.loginguestnext);
                loginnext.show();
                final EditText emaildialog = loginnext.findViewById(R.id.emaildialog);
                final EditText passwordialog = loginnext.findViewById(R.id.passworddialog);
                Button submit = loginnext.findViewById(R.id.signindialog);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

                        Call<LoginResponseList> userCall = service.insertlogin(emaildialog.getText().toString(), passwordialog.getText().toString());

                        userCall.enqueue(new Callback<LoginResponseList>() {
                            @Override
                            public void onResponse(Call<LoginResponseList> call, Response<LoginResponseList> response) {
                                ArrayList<LoginResponse> mainview = (ArrayList<LoginResponse>) response.body().getLoginResponse();

                                if (mainview != null) {
                                    for (int i = 0; i < mainview.size(); i = i + 1) {

                                        if (mainview.get(i).getSuccess().equals("1")) {
//                                                    Toast.makeText(getActivity(), "sssss", Toast.LENGTH_SHORT).show();
                                            DtabaeHelper mydb = new DtabaeHelper(MainActivity.this);
                                            Cursor cursor = mydb.checkaquatic();
                                            if (cursor.getCount() == 0) {
                                                mydb.insertauatic(mainview.get(i).getEmail().toString(), mainview.get(i).getName().toString(), "guestcode", "Guest", mainview.get(i).getPhonenumber().toString(), "","","","","","","");
                                                loginnext.dismiss();

                                                Intent in = new Intent(MainActivity.this, MainActivity.class);

                                                startActivity(in);
                                                finish();
                                            } else {
                                                mydb.updateaquatic("1", mainview.get(i).getEmail().toString(), mainview.get(i).getName().toString(), "guestcode", "Guest", mainview.get(i).getPhonenumber().toString(), "","","","","","","");
                                                loginnext.dismiss();

                                                Intent in = new Intent(MainActivity.this, MainActivity.class);

                                                startActivity(in);
                                                finish();

                                            }

                                        } else if (mainview.get(i).getSuccess().equals("0")) {
                                            loginnext.show();
                                            Toast.makeText(MainActivity.this, "Wrong Email OR Password", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponseList> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                Log.d("onFailure", t.toString());
                            }
                        });

//                                Toast.makeText(getActivity(), "LogIn", Toast.LENGTH_SHORT).show();
                    }
                });
                TextView newsignup = loginnext.findViewById(R.id.newsignup);
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

    public void topselles(final String teken)
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<Topsellerslist> call = apiService.gettopsellers("https://cpecintel.com/aquatic/topseller/topseller.php");
        call.enqueue(new Callback<Topsellerslist>() {
            @Override
            public void onResponse(Call<Topsellerslist> call, Response<Topsellerslist> response) {
                int statusCode = response.code();

                ArrayList<Topsellesr> topesellers = (ArrayList<Topsellesr>) response.body().getTopsellesr();
                if (topesellers!=null) {
ArrayList<String> lists=new ArrayList<>();
                    for(int c=0;c<topesellers.size();c++)
                    {
                      lists.add(topesellers.get(c).getEmail());
                      if(topesellers.size()-c==1)
                      {
                          if(checkemail.equals(""))
                          {

                          }
                          else {
                              if (lists.contains(checkemail)) {
                                  // Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
                                  getnofication();
                                  insertcode(teken);
                         //         Toast.makeText(MainActivity.this, "hiii", Toast.LENGTH_SHORT).show();

                              } else {
//                              Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                              }
                          }
                      }
                    }




                }
            }
            @Override
            public void onFailure(Call<Topsellerslist> call, Throwable t) {
                topselles(FirebaseInstanceId.getInstance().getToken());
            }
        });
    }

    public void getnofication()
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<MSG> call = apiService.getnotification("https://cpecintel.com/aquatic/login/notificationnum.php");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                int statusCode = response.code();

//                Toast.makeText(MainActivity.this, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();

                if (response.body()!=null) {
//                    Toast.makeText(MainActivity.this, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();
notification=response.body().getSuccess();


                }
            }
            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                getnofication();
            }
        });
    }

    public void adminlists(final String teken)
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<AdminResponseLists> call = apiService.getadminslist("https://cpecintel.com/aquatic/chat/adminlist.php");
        call.enqueue(new Callback<AdminResponseLists>() {
            @Override
            public void onResponse(Call<AdminResponseLists> call, Response<AdminResponseLists> response) {
                int statusCode = response.code();

                ArrayList<Adminslist> topesellers = (ArrayList<Adminslist>) response.body().getAdminslist();
                if (topesellers!=null) {
                    ArrayList<String> lists=new ArrayList<>();
                    for(int c=0;c<topesellers.size();c++)
                    {
                        lists.add(topesellers.get(c).getEmail());
                        if(topesellers.size()-c==1)
                        {
                            if(lists.contains(checkemail))
                            {
                                insertcodeadmin(teken);
usertype="admin";
                            }
                            else
                            {
                                usertype="user";
//                              Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                            }
                            MainActivity.chaticon.setVisibility(View.VISIBLE);
                        }
                    }




                }
            }
            @Override
            public void onFailure(Call<AdminResponseLists> call, Throwable t) {
                adminlists(FirebaseInstanceId.getInstance().getToken());
            }
        });
    }

public void insertcode(String code)
{
    ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

    Call<MSG> userCall = service.inserttoken(code);

    userCall.enqueue(new Callback<MSG>() {
        @Override
        public void onResponse(Call<MSG> call, Response<MSG> response) {

            Log.d("onResponse", "" + response.body().getMessage());



        }

        @Override
        public void onFailure(Call<MSG> call, Throwable t) {
            /*try {
                Toast.makeText(getContext(), "You don't have internet connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
           insertcode(FirebaseInstanceId.getInstance().getToken());
                }
    });

}
    public void insertcodeadmin(String code)
    {
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<MSG> userCall = service.insertadmin(code);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {

                Log.d("onResponse", "" + response.body().getMessage());



            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
            /*try {
                Toast.makeText(getContext(), "You don't have internet connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
                insertcodeadmin(FirebaseInstanceId.getInstance().getToken());        }
        });

    }
public void chatfragment(View v)
{
    if(checkemail.equals(""))
    {
        Toast.makeText(MainActivity.this, "Please LogIn First..", Toast.LENGTH_SHORT).show();
    }
    else {
        if (usertype.equals("admin")) {
            chaticon.setVisibility(View.GONE);
            MainActivity.t.cancel();
            MainActivity.backfragment = MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentAdmin_Guest_List("");
        } else if (usertype.equals("user")) {
            chaticon.setVisibility(View.GONE);
            MainActivity.t.cancel();
            MainActivity.backfragment = MainActivity.currentfragment;
            MainActivity.mNavigationManager.showFragmentChatFragment("");
        }
    }
}
public static void chaticonmain()
{
    chaticon.setVisibility(View.GONE);
}
    @Override
    protected void onStop() {

        super.onStop();
    }
}
