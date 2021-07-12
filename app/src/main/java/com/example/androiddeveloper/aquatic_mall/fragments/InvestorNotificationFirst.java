package com.example.androiddeveloper.aquatic_mall.fragments;



        import android.app.Dialog;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.content.ContextCompat;
        import android.support.v4.view.ViewPager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.androiddeveloper.aquatic_mall.Adapters.AboutAdapter;
        import com.example.androiddeveloper.aquatic_mall.Adapters.InvestorNotificationAdapterFirst;
        import com.example.androiddeveloper.aquatic_mall.FireBaseConnection.Topsellesr;
        import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
        import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
        import com.example.androiddeveloper.aquatic_mall.MainActivity;
        import com.example.androiddeveloper.aquatic_mall.R;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.*;
        import com.example.androiddeveloper.aquatic_mall.requesttobook.*;
        import com.example.androiddeveloper.aquatic_mall.requesttobook.LoginAsInvestor;

        import java.util.ArrayList;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;


/**
 * Created by ANDROID DEVELOPER on 02/02/2018.
 */

public class InvestorNotificationFirst extends Fragment {

ViewPager viewPager;
InvestorNotificationAdapterFirst investorNotificationAdapterFirst;
Button acceptrequestbtn;
    ImageView img,back,next;
    LinearLayout infolayout,backbress;
    TextView accepted;
    ArrayList<String> checkarray=new ArrayList<>();
    Dialog dialog;
public static ArrayList<Notificationdataresponse> notificationdata=new ArrayList<>();
    ArrayList<Notificationresponse> notifications1=new ArrayList<>();
    public static InvestorNotificationFirst newInstance(String movieTitle) {
        InvestorNotificationFirst fragmentAction = new InvestorNotificationFirst();
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
        return inflater.inflate(R.layout.topseller, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img=view.findViewById(R.id.loadingnotification);
        back=view.findViewById(R.id.back);
        backbress=view.findViewById(R.id.backbress);
        next=view.findViewById(R.id.next);
        infolayout=view.findViewById(R.id.inoflayout);
        acceptrequestbtn=view.findViewById(R.id.accpetrequest);
        accepted=view.findViewById(R.id.accepeted);
        viewPager = (ViewPager) view.findViewById(R.id.infopager);
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.loadingshoppingdialog);

        acceptrequestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

                getnotification(String.valueOf(notifications1.get(viewPager.getCurrentItem()).getId()));
 //               Toast.makeText(getActivity(), ""+notifications1.get(viewPager.getCurrentItem()).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        backbress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backpressed();
            }
        });
        getnofication();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
if(checkarray.get(position).equals("valid"))
{
    accepted.setVisibility(View.GONE);
    acceptrequestbtn.setEnabled(true);
    final int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        acceptrequestbtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.background_button2) );
    } else {
        acceptrequestbtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.background_button2));
    }

}
else
{
    accepted.setVisibility(View.VISIBLE);
    acceptrequestbtn.setEnabled(false);
    final int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        acceptrequestbtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.background_button) );
    } else {
        acceptrequestbtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.background_button));
    }

}
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(viewPager.getCurrentItem()==0)
              {

              }
              else
              {
viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
              }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notifications1.size()-viewPager.getCurrentItem()==1)
                {

                }
                else
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                }
            }
        });
    }
    public void getnofication()
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<NotificationResponseLists> call = apiService.notificationresponse("https://cpecintel.com/aquatic/topseller/getallnotifications.php");
        call.enqueue(new Callback<NotificationResponseLists>() {
            @Override
            public void onResponse(Call<NotificationResponseLists> call, Response<NotificationResponseLists> response) {
                int statusCode = response.code();

                ArrayList<Notificationresponse> notifications = (ArrayList<Notificationresponse>) response.body().getNotificationresponse();
                if (notifications!=null) {
                    for(int c=0;c<notifications.size();c++)
                    {
                        notifications1.add(notifications.get(c));
                        checkarray.add("valid");

                    }
                      investorNotificationAdapterFirst = new InvestorNotificationAdapterFirst(getActivity(),notifications);
                    viewPager.setAdapter(investorNotificationAdapterFirst);
                    img.setVisibility(View.GONE);
                   infolayout.setVisibility(View.VISIBLE);
                   if(notifications.size()==1)
                   {
                       back.setVisibility(View.GONE);
                       next.setVisibility(View.GONE);
                   }
//                    ArrayList<String> lists=new ArrayList<>();




                }
            }
            @Override
            public void onFailure(Call<NotificationResponseLists> call, Throwable t) {
                getnofication();
            }
        });
    }


    public void getnotification(String id)
    {
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NotificationdataList> userCall =service.getnotificationdata(id);

        userCall.enqueue(new Callback<NotificationdataList>() {
            @Override
            public void onResponse(Call<NotificationdataList> call, Response<NotificationdataList> response) {

                ArrayList<Notificationdataresponse> mainview = (ArrayList<Notificationdataresponse>) response.body().getNotificationdataresponse();
                dialog.dismiss();
if(mainview!=null)
{
    notificationdata.clear();
    for(int c=0;c<mainview.size();c++)
    {
        if(mainview.get(c).getStatus().equals("unaccepted"))
        {
            notificationdata.add(mainview.get(c));
                            MainActivity.backfragment = MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentBookingRequestDetail("");
        }
        else
        {
            checkarray.set(viewPager.getCurrentItem(),"unvalid");
            accepted.setVisibility(View.VISIBLE);
            acceptrequestbtn.setEnabled(false);
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                acceptrequestbtn.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.background_button) );
            } else {
                acceptrequestbtn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.background_button));
            }

        }

    }
    MainActivity.notification=MainActivity.notification-1;

}

            }

            @Override
            public void onFailure(Call<NotificationdataList> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
