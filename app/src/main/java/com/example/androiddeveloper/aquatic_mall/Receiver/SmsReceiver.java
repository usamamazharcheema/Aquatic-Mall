package com.example.androiddeveloper.aquatic_mall.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.SmsListener;

public class SmsReceiver extends BroadcastReceiver {


    final SmsManager sms = SmsManager.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        // Get the SMS message received
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                // A PDU is a "protocol data unit". This is the industrial standard for SMS message
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    // This will create an SmsMessage object from the received pdu
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    // Get sender phone number
                    String phoneNumber = sms.getDisplayOriginatingAddress();
                    String sender = phoneNumber;
                    String message = sms.getDisplayMessageBody();
                   /* String formattedText = String.format(context.getResources().getString(R.string.sms_message), sender, message);
                */    // Display the SMS message in a Toast
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    /*MainActivity inst = MainActivity.instance();
                    inst.updateList(formattedText);*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}