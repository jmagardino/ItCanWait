package com.joechristy.itcanwait;


import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class MyTimer extends CountDownTimer{
		
		private static boolean isCountingFlag = false;
		private CountdownActivity activity;
		private static String category;
		SharedPreferences sharedPreferences;
		
		int points;
		
		
		public static String getCategory(){
			return category;
		}
		public static boolean isCounting(){
			return isCountingFlag;
		}
		public static void setCountingFlagFalse(){
			isCountingFlag = false;
		}
		
		
		public MyTimer(CountdownActivity activity, long millisInFuture, long countDownInterval, String category) {
            super(millisInFuture, countDownInterval);
            points = (int) TimeUnit.MILLISECONDS.toSeconds(millisInFuture);
            this.activity = activity;
            this.category = category;
            isCountingFlag = true;
            // TODO Auto-generated constructor stub
            //tv.setText("changed by the constructor");
            
		}

		
		@Override
		public void onFinish() {
			isCountingFlag=false;
			saveScore();
			activity.sendEmail();	
			activity.updateViewText("Done!");
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			activity.updateView(millisUntilFinished);
			//CountdownActivity.setMillisRemaining(millisUntilFinished);
		}
		
		private void saveScore()
		{
			sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
			int currentScore = sharedPreferences.getInt(category+"Key", 0);	
			SharedPreferences.Editor editor = sharedPreferences.edit();			
			editor.putInt(category+"Key", currentScore + points);
			editor.commit();
		}
		
		
	}
