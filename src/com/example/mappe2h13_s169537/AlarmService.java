package com.example.mappe2h13_s169537;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AlarmService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.HOUR_OF_DAY, 11);
		cal.set(cal.MINUTE, 45);
		cal.set(cal.SECOND, 00);

		Intent i = new Intent(this, SMSservice.class);
		PendingIntent pintent = PendingIntent.getService(this, 0, i,
				PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pintent);

		return super.onStartCommand(intent, flags, startId);
	}

}
