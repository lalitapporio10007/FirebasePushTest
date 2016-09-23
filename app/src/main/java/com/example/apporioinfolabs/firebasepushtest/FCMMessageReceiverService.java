package com.example.apporioinfolabs.firebasepushtest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by apporioinfolabs on 16-09-2016.
 */
public class FCMMessageReceiverService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e("fcm", "received notification"+remoteMessage);

        sendNotification(remoteMessage.getNotification().getBody());

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e("", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e("", "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e("", "Message Notification Body: " + remoteMessage.getNotification().getBody());

            sendNotification(remoteMessage.getNotification().getBody());

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


    }

    private RemoteViews getComplexNotificationView() {
        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews notificationView = new RemoteViews(
                getApplicationContext().getPackageName(),
                R.layout.custom_noti_layout
        );
        notificationView.setImageViewResource(R.id.customiamge, R.drawable.childbook);

        // Locate and set the Text into customnotificationtext.xml TextViews
//        notificationView.setTextViewText(R.id.title, "helllll");
//        notificationView.setTextViewText(R.id.text, "hhhhhhh");

        return notificationView;

    }

    private NotificationCompat.Builder sendNotification(String messageBody) {
        Intent intent = new Intent(this, default_activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);
     // startActivity(intent);

       // RemoteViews removeWidget = new RemoteViews(getApplicationContext().getPackageName(), R.layout.custom_noti_layout);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageBody)
                .setAutoCancel(false)
                .setSound(defaultSoundUri)

                .setContent(getComplexNotificationView()) ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // build a complex notification, with buttons and such
            //
            notificationBuilder = notificationBuilder.setContent(getComplexNotificationView());
        } else {
            // Build a simpler notification, without buttons
            //
            notificationBuilder = notificationBuilder.setContentTitle("image")
                    .setContentText("test")
                    .setSmallIcon(android.R.drawable.ic_menu_gallery);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notificationBuilder.build());

        return notificationBuilder;
    }

}
