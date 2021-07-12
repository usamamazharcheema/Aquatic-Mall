package com.example.androiddeveloper.aquatic_mall.Adapters;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.LookingToInvest;
import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.Topsellerslist;
import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.Topsellesr;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LoginResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.example.androiddeveloper.aquatic_mall.fragments.feature_model;
import com.example.androiddeveloper.aquatic_mall.requesttobook.LogIn_asGUest_Register;
import com.example.androiddeveloper.aquatic_mall.requesttobook.LoginAsInvestor;
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LookinToInvestAdapter extends RecyclerView.Adapter<LookinToInvestAdapter.MyViewHolder> {

    public int numrows;
    String id=null;
    String b=null;
    Bundle savedInstanceState;
    ArrayList<feature_model> mainlist;
    Context context;


    public LookinToInvestAdapter(int numrows, Context context, ArrayList<feature_model> mainlist) {
        this.numrows = numrows;
        this.context = context;
        this.mainlist = mainlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv,area,price;
        LinearLayout requestobook;

        public RelativeLayout onclickmain;
//        MapView map;

        public MyViewHolder(View view) {
            super(view);
//            onclickmain=(RelativeLayout)view.findViewById(R.id.onclickmain);
            area = (TextView) view.findViewById(R.id.area);
            price = (TextView) view.findViewById(R.id.price);
            requestobook = (LinearLayout) view.findViewById(R.id.requestobook);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.investlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

//                hidepDialog();
        //onSignupSuccess();

        holder.area.setText(mainlist.get(position).getSqfeet()+" sqft");
        holder.price.setText(mainlist.get(position).getPrice()+" pkr");
        holder.requestobook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialogfirst;


                dialogfirst=new Dialog(context);
                dialogfirst.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogfirst.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialogfirst.setContentView(R.layout.requestfirst);
                dialogfirst.show();
                Button yes,no;
                yes=dialogfirst.findViewById(R.id.requestyes);
                no=dialogfirst.findViewById(R.id.requestno);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogfirst.dismiss();
                    }
                });
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogfirst.dismiss();





                if(MainActivity.Logindtect.equals("logout"))
                {




















                    final Dialog dialog;


                    dialog=new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dialog.setContentView(R.layout.loginpopupfirst);
                    Button invesor=dialog.findViewById(R.id.investorcheck);
                    invesor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent i=new Intent(context,LoginAsInvestor.class);
                            i.putExtra("floor",mainlist.get(position).getFloor().toString());
                            i.putExtra("shopcode",mainlist.get(position).getShopno().toString());
                            i.putExtra("item", LookingToInvest.shoplist.get(LookingToInvest.viewPager.getCurrentItem()));
                            context.startActivity(i);
                        }
                    });
                    Button guest=dialog.findViewById(R.id.guestcheck);
                    guest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();
                            final Dialog loginnext;
                            loginnext=new Dialog(context);
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
                                                        DtabaeHelper mydb=new DtabaeHelper(context);
                                                        Cursor cursor = mydb.checkaquatic();
                                                        if (cursor.getCount() == 0) {
                                                            mydb.insertauatic(mainview.get(i).getEmail().toString(),mainview.get(i).getName().toString(),"guestcode","Guest",mainview.get(i).getPhonenumber().toString(),"","","","","","","");
                                                            loginnext.dismiss();

                                                            Intent in = new Intent(context, MainActivity.class);

                                                            context.startActivity(in);

                                                        } else {
                                                            mydb.updateaquatic("1",mainview.get(i).getEmail().toString(),mainview.get(i).getName().toString(),"guestcode","Guest",mainview.get(i).getPhonenumber().toString(),"","","","","","","");
                                                            final Dialog loading;
                                                            loading = new Dialog(context);
                                                            loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                            loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                                            loading.setContentView(R.layout.loadingshoppingdialog);
                                                            loading.show();
                                                            MainActivity.checkemail=mainview.get(0).getEmail().toString();
                                                            MainActivity.name=mainview.get(0).getName().toString();
                                                            MainActivity.phone=mainview.get(0).getPhonenumber().toString();
                                                            MainActivity.cnic=" ";
                                                            MainActivity.investorcode="guestcode";
                                                            MainActivity.Logindtect="Guest";




                                                            String name= MainActivity.name;
                                                            String email= MainActivity.checkemail;
                                                            String phone= MainActivity.phone;
                                                            String shopcode=mainlist.get(position).getShopno().toString();
                                                            String floorno=mainlist.get(position).getFloor().toString();



                                                            sendnotification();

                                                            ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                                                            Call<MSG> userCall =service.requestobook(name,email,phone,"","",shopcode,floorno, LookingToInvest.shoplist.get(LookingToInvest.viewPager.getCurrentItem()));

                                                            userCall.enqueue(new Callback<MSG>() {
                                                                @Override
                                                                public void onResponse(Call<MSG> call, Response<MSG> response) {
                                                                    loading.dismiss();
                                                                    if(response.body().getSuccess()==1)
                                                                    {
                                                                        loginnext.dismiss();
                                                                        final Dialog dialog;
                                                                        dialog=new Dialog(context);
                                                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                                        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                                                        dialog.setContentView(R.layout.requestresponsedialog);
                                                                        Button ok=dialog.findViewById(R.id.ok);
                                                                        ok.setOnClickListener(new View.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(View v) {
                                                                                dialog.dismiss();
                                                                            }
                                                                        });
                                                                        dialog.show();
                                                                    }

//                    Toast.makeText(context, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onFailure(Call<MSG> call, Throwable t) {
                                                                    Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                                                                    Log.d("onFailure", t.toString());
                                                                }
                                                            });



                                                        }

                                                    }
                                                    else if (mainview.get(i).getSuccess().equals("0"))
                                                    {
                                                        loginnext.show();
                                                        Toast.makeText(context, "Wrong Email OR Password", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<LoginResponseList> call, Throwable t) {
                                            Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
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
                                    Intent i=new Intent(context,LogIn_asGUest_Register.class);
                                    i.putExtra("floor",mainlist.get(position).getFloor().toString());
                                    i.putExtra("shopcode",mainlist.get(position).getShopno().toString());
                                    i.putExtra("item", LookingToInvest.shoplist.get(LookingToInvest.viewPager.getCurrentItem()));
                                    context.startActivity(i);



                                }
                            });


                        }
                    });
                    dialog.show();

























                }
                else
                {
                    final Dialog loading;
                    loading = new Dialog(context);
                    loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    loading.setContentView(R.layout.loadingshoppingdialog);
                    loading.show();




                    String name= MainActivity.name;
                    String email= MainActivity.checkemail;
                    String phone= MainActivity.phone;
                    String shopcode=mainlist.get(position).getShopno().toString();
                    String floorno=mainlist.get(position).getFloor().toString();


                    sendnotification();


                    ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                    Call<MSG> userCall =service.requestobook(name,email,phone,"","",shopcode,floorno,LookingToInvest.shoplist.get(LookingToInvest.viewPager.getCurrentItem()));

                    userCall.enqueue(new Callback<MSG>() {
                        @Override
                        public void onResponse(Call<MSG> call, Response<MSG> response) {
                            loading.dismiss();
                            if(response.body().getSuccess()==1)
                            {

                                final Dialog dialog;
                                dialog=new Dialog(context);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                dialog.setContentView(R.layout.requestresponsedialog);
                                Button ok=dialog.findViewById(R.id.ok);
                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }

//                    Toast.makeText(context, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<MSG> call, Throwable t) {
                            Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                            Log.d("onFailure", t.toString());
                        }
                    });










//            Toast.makeText(context, ""+name+" "+email+" "+phone+" "+shopcode+" "+floorno, Toast.LENGTH_SHORT).show();
                }
                    }
                });
            }
        });


    }
    @Override
    public int getItemCount() {
        return mainlist.size();
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







       /* ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MSG> userCall =service.sendnotification("");

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {




            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
//                Toast.makeText(LoginAsInvestor.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
//                Log.d("onFailure", t.toString());
            }
        });*/
    }

}