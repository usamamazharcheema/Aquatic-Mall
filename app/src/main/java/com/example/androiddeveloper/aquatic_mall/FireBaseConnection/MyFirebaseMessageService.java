package com.example.androiddeveloper.aquatic_mall.FireBaseConnection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.androiddeveloper.aquatic_mall.Activities.NotificationActivity;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageService extends FirebaseMessagingService {
    public MyFirebaseMessageService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification().getTag().equals("booking"))
        {
            sendnotification(remoteMessage.getNotification().getBody());
        }
        else if(remoteMessage.getNotification().getTag().equals("chat"))
        {
            sendnotification(remoteMessage.getNotification().getBody());
            if(remoteMessage.getNotification().getTitle().equals("user"))
            {
               /* SharedPreferences sharedpreferencesuser ;
                String mypreferencecheck = "checkuserrfragment";
                String status = "usercondition";
                sharedpreferencesuser = getSharedPreferences(mypreferencecheck,
                        Context.MODE_PRIVATE);

                if (sharedpreferencesuser.contains(status)) {
                  if( sharedpreferencesuser.getString(status, "").equals("no"))
                    {

                    }

                }*/


                SharedPreferences sharedpreferences ;
                String mypreference = "guestmessagepreference";
                String message = "guestmessage";
                sharedpreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(message, remoteMessage.getNotification().getBody());

                editor.commit();
//                DtabaeHelper mydb=new DtabaeHelper(getApplicationContext());
//                mydb.updateusertable("1",remoteMessage.getNotification().getBody());

            }
            else
            {
                SharedPreferences sharedpreferences ;
                String mypreference = "adminmessagepreference";
                String adminmessage = "adminmessage";
                 String adminemail = "adminemail";
                sharedpreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(adminmessage, remoteMessage.getNotification().getBody());
                editor.putString(adminemail, remoteMessage.getNotification().getTitle());

                editor.commit();
//                DtabaeHelper mydb=new DtabaeHelper(getApplicationContext());
//                mydb.updateadmintable("1",remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

            }
        }

    }
    public void sendnotification(String messagebody)
    {
        Intent i=new Intent(this, NotificationActivity.class);
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)


                        .setContentTitle("The Aquatic Mall")
                        .setAutoCancel(true)
                        .setSound(uri)

                        .setContentText(messagebody);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.newicon);
            mBuilder.setColor(getResources().getColor(R.color.colorAccent));
        } else {
            mBuilder.setSmallIcon(R.drawable.newicon);
        }

        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//


        mNotificationManager.notify(0,mBuilder.build());
}
}
