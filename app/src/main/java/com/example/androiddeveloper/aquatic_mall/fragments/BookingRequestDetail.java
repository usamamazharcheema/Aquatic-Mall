package com.example.androiddeveloper.aquatic_mall.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;

/**
 * Created by ANDROID DEVELOPER on 10/03/2018.
 */



import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.R;

import org.w3c.dom.Text;


/**
 * Created by ANDROID DEVELOPER on 02/02/2018.
 */

public class BookingRequestDetail extends Fragment {

    TextView name,email,cnic,cellno,unitno,code,floorno,call,whatsapp,iteminfo;
    public static BookingRequestDetail newInstance(String movieTitle) {
        BookingRequestDetail fragmentAction = new BookingRequestDetail();

        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    LinearLayout back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_notification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back=view.findViewById(R.id.noti_back);
        name=view.findViewById(R.id.noti_name);
        email=view.findViewById(R.id.noti_email);
        cellno=view.findViewById(R.id.noti_cellno);
        cnic=view.findViewById(R.id.noti_cnic);
        unitno=view.findViewById(R.id.noti_unitno);
        code=view.findViewById(R.id.noti_code);
        call=view.findViewById(R.id.noti_call);
        whatsapp=view.findViewById(R.id.noti_whatsapp);
        floorno=view.findViewById(R.id.noti_floor);
        iteminfo=view.findViewById(R.id.iteminfo);
        name.setText(InvestorNotificationFirst.notificationdata.get(0).getName().toString());
        email.setText(InvestorNotificationFirst.notificationdata.get(0).getEmail().toString());
        cellno.setText(InvestorNotificationFirst.notificationdata.get(0).getPhone().toString());
        cnic.setText(InvestorNotificationFirst.notificationdata.get(0).getCnic().toString());
        unitno.setText(InvestorNotificationFirst.notificationdata.get(0).getUnitno().toString());
        floorno.setText(InvestorNotificationFirst.notificationdata.get(0).getFloorno().toString());
        code.setText(InvestorNotificationFirst.notificationdata.get(0).getShopcode().toString());
        iteminfo.setText(InvestorNotificationFirst.notificationdata.get(0).getItem().toString()+" Information");
call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try
        {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String p = "tel:" + cellno.getText().toString();
        i.setData(Uri.parse(p));
        startActivity(i);
        getActivity().overridePendingTransition(R.anim.layoutup, R.anim.backpressedlayout);
        }
        catch (Exception e)
        {

        }
    }
});
whatsapp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            Uri uri = Uri.parse("smsto:" + cellno.getText().toString());
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.putExtra("sms_body", "Hi, I am the top seller of The Aquatic Mall " + name.getText().toString());
            i.setPackage("com.whatsapp");
            startActivity(i);
        }
        catch (Exception e)
        {

        }
    }
});
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.backpressed();
    }
});
    }
}
