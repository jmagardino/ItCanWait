	package com.joechristy.itcanwait;
// this is a comment
	//oncreate is not being called when we come back i dont think so we are not updating the text view!
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button mHomeworkButton;
	private Button mDrivingButton;
	private Button mFamilyButton;
	private Button mFriendsButton;
	private Button mStudyButton;
	private Button mMovieButton;
	private Button mSharePointButton;
	
	private String mHomeworkFlag="homework";
	private String mFamilyFlag="family";
	private String mStudyFlag="study";
	private String mDrivingFlag="driving";
	private String mFriendsFlag="friends";
	private String mMovieFlag="movie";
	
	private EditText mEmailTextField;
	private TextView mScoreTextField;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		mScoreTextField = (TextView)findViewById(R.id.score_text);
		mScoreTextField.setText(Integer.toString(setScore()));
		
		mEmailTextField = (EditText)findViewById(R.id.email_textfield);
		mSharePointButton = (Button)findViewById(R.id.share_point_button);
		mSharePointButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String message = "I have earned " + setScore() + " points so far in ItCanWait! " +
						"See how many you can get by downloading the app!!!";
				String subject = "Check out my points!";
				String textTo = mEmailTextField.getText().toString();
				
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{textTo});
				email.putExtra(Intent.EXTRA_SUBJECT, subject);
				email.putExtra(Intent.EXTRA_TEXT, message);
				
				email.setType("message/rfc822");
				
				startActivity(Intent.createChooser(email, "Choose an email client"));
			}
		});
		
		mHomeworkButton = (Button)findViewById(R.id.homework_button);
		mHomeworkButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TimePicker.class);
				i.putExtra("actionFlag", mHomeworkFlag);
				startActivity(i);
			}
		});
		
		
		
		
		mFamilyButton = (Button)findViewById(R.id.family_button);
		mFamilyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TimePicker.class);
				i.putExtra("actionFlag", mFamilyFlag);
				startActivity(i);
			}
		});
		
		
		mStudyButton = (Button)findViewById(R.id.study_button);
		mStudyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TimePicker.class);
				i.putExtra("actionFlag", mStudyFlag);
				startActivity(i);
			}
		});
		
		
		
		mDrivingButton = (Button)findViewById(R.id.driving_button);
		mDrivingButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TimePicker.class);
				i.putExtra("actionFlag", mDrivingFlag);
				startActivity(i);
			}
		});
		
		
		mFriendsButton = (Button)findViewById(R.id.friends_button);
		mFriendsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TimePicker.class);
				i.putExtra("actionFlag", mFriendsFlag);
				startActivity(i);
			}
		});
		
		
		mMovieButton = (Button)findViewById(R.id.movie_button);
		mMovieButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TimePicker.class);
				i.putExtra("actionFlag", mMovieFlag);
				startActivity(i);
			}
		});

	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		mScoreTextField = (TextView)findViewById(R.id.score_text);
		mScoreTextField.setText(Integer.toString(setScore()));
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private int setScore()
	{
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int score = 0;
		
		//if(sharedPreferences.contains("homeworkKey"))
			score =score + sharedPreferences.getInt("homeworkKey", 0);
		
		//if(sharedPreferences.contains("drivingKey")) 
			score =score + sharedPreferences.getInt("drivingKey", 0);

	//	if(sharedPreferences.contains("friendsKey"))
			score =score + sharedPreferences.getInt("friendsKey", 0);

		//if(sharedPreferences.contains("studyKey"))
			score =score + sharedPreferences.getInt("studyKey", 0);

		//if(sharedPreferences.contains("familyKey"))
			score =score + sharedPreferences.getInt("familyKey", 0);

		//if(sharedPreferences.contains("movieKey"))
			score =score + sharedPreferences.getInt("movieKey", 0);

			
			return score;
	}
}
