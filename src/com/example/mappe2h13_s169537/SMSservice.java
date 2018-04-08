package com.example.mappe2h13_s169537;

import java.text.DecimalFormat;
import java.util.Calendar;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSservice extends Service {
	DBAdapter db;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		getSystemService(NOTIFICATION_SERVICE);

		smscontacts();

		return super.onStartCommand(intent, flags, startId);
	}

	private void smscontacts() {
		db = new DBAdapter(this);
		db.open();
		Calendar cal = Calendar.getInstance();
		final DecimalFormat datef = new DecimalFormat("00");
		String Day = datef.format(Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH));
		String Month = datef
				.format(Calendar.getInstance().get(Calendar.MONTH) + 1);
		String Year = Calendar.getInstance().get(Calendar.YEAR) + "";
		String date = (Day + "/" + Month + "/" + Year);
		cal.get(Calendar.DAY_OF_MONTH);
		cal.get(Calendar.MONTH);
		cal.get(Calendar.DAY_OF_YEAR);
		Toast.makeText(this, date, Toast.LENGTH_LONG).show();
		Cursor c = db.FindBirthday(date);
		if (c.moveToFirst()) {
			do {
				sendSMS(c.getString(3), c.getString(5));
			} while (c.moveToNext());
		}
		db.close();
		c.close();
	}

	private void sendSMS(String phoneNumber, String message) {
		SmsManager sms = SmsManager.getDefault();
		Toast.makeText(this, R.string.sms, Toast.LENGTH_LONG).show();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
