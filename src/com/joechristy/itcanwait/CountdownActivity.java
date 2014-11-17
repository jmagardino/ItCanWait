package com.joechristy.itcanwait;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CountdownActivity extends Activity {

	//public static long millisRemaining;
	
	TextView tv;
	TextView categoryScore;
	MyTimer timer;
	
	SharedPreferences sharedPreferences;
	
	int points;
	
	//public static void setMillisRemaining(long millisInput){
	//	millisRemaining = millisInput;
	//}
	
	public void updateView(long millisUntilFinished){
		tv.setText(""+String.format("%d : %d : %d",
				TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                	TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 
               	TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
	}
	
	public void updateViewText(String text){
		tv.setText(text);
	}
	
	public void sendEmail()
	{		
		String message = "You have earned " + points + " points in " + getIntent().getExtras().getString("actionFlag") + " category! " +
				"Thank you for participating in ItCanWait!!!";
		String subject = "New Points!!!";
		String textTo = "jmagardino@gmail.com";
		
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{textTo});
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, message);
		
		email.setType("message/rfc822");
		
		startActivity(Intent.createChooser(email, "Choose an email client"));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countdown);
		
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		tv = (TextView)findViewById(R.id.textView2);
		
		categoryScore = (TextView)findViewById(R.id.categoryScore);
		categoryScore.setText(Integer.toString(sharedPreferences.getInt(getIntent().getExtras().getString("actionFlag")+"Key", 0)));
		
		int hour = getIntent().getExtras().getInt("hour");
		int min = getIntent().getExtras().getInt("minute");
		
		points = hour * 60 * 60 + min * 60;
		
		//if(savedInstanceState != null){
		//	timer = new MyTimer(this, savedInstanceState.getLong("millisLeft"), (long)1000,getIntent().getExtras().getString("actionFlag"));
		//}
		//else{
			timer = new MyTimer(this, (long)(hour*60*60*1000)+(min*60*1000),(long)1000, getIntent().getExtras().getString("actionFlag"));
			timer.start();
	//	}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		MyTimer.setCountingFlagFalse();
		timer.cancel();
		super.onPause();
		//finish();
	}

	
	/**
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putLong("millisLeft", millisRemaining);
	}
	*/
	
	
	
	
	
	
}	
	
	
