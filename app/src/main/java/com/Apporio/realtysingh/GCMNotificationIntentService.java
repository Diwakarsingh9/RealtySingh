package com.Apporio.realtysingh;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import java.util.ArrayList;

//
public class GCMNotificationIntentService extends GcmListenerService {
//	// Sets an ID for the notification, so it can be updated
	public static final int notifyID = 9001;
	public  static ArrayList<String> message11 = new ArrayList<>();
//	NotificationCompat.Builder builder;
		public static Intent resultIntent;



	@Override
	public void onMessageReceived(String from, Bundle data) {
		message11.clear();
		String msg = data.getString("message");;
		sendNotification(msg);
	}


	private void sendNotification(String message) {;//
		if(Notificationclass.activityopen==true){//
			 resultIntent = new Intent(this, MainActivity.class);//
			resultIntent.putExtra("msg", message);//
		}//
		else {//
			 resultIntent = new Intent(this, SplashActivity.class);//
			resultIntent.putExtra("msg", message);//
		}//
//		MainActivity.activity22.finish();//
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,//
				resultIntent, PendingIntent.FLAG_ONE_SHOT);//
//
		NotificationCompat.Builder mNotifyBuilder;//
		NotificationManager mNotificationManager;//
//
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);//
//
		mNotifyBuilder = new NotificationCompat.Builder(this)//
				.setContentTitle("Realty Singh")//
				.setContentText("" + message)//
				.setSmallIcon(R.drawable.logo);//
		// Set pending intent//
		mNotifyBuilder.setContentIntent(resultPendingIntent);//
//
		// Set Vibrate, Sound and Light//
		int defaults = 0;//
		defaults = defaults | Notification.DEFAULT_LIGHTS;//
		defaults = defaults | Notification.DEFAULT_VIBRATE;//
		defaults = defaults | Notification.DEFAULT_SOUND;//
//
		mNotifyBuilder.setDefaults(defaults);//
		// Set the content for Notification//
		mNotifyBuilder.setContentText(""+message);//
		// Set autocancel//
		mNotifyBuilder.setAutoCancel(true);//
		// Post a notification//
		mNotificationManager.notify(notifyID, mNotifyBuilder.build());//




	}
}