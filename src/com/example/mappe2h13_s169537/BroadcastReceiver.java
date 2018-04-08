package com.example.mappe2h13_s169537;

import android.content.Context;
import android.content.Intent;

public class BroadcastReceiver extends android.content.BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, AlarmService.class);
		context.startService(i);

	}

}
