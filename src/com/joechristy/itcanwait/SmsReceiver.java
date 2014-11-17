package com.joechristy.itcanwait;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	private static final String TAG = "SmsReceiver";
	private static int mNextNotificationId = 1;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Bundle bundle = intent.getExtras();
		
		
		if(MyTimer.isCounting())
			{
			if(bundle!= null){
				Object[] pdus = (Object[])bundle.get("pdus");
				
				for(Object pdu : pdus){
					SmsMessage message = SmsMessage.createFromPdu((byte[])pdu);
					String originatingNumber = message.getOriginatingAddress();
					
					try{
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(originatingNumber, null, "Sorry I am busy in the " + MyTimer.getCategory() + " category in the ItCanWaitApp"  , null, null);
					}catch(Exception ex){
						//do nothing
					}
				}
			}
		}
		
	}

}
