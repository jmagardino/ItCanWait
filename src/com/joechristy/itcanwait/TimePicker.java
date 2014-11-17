package com.joechristy.itcanwait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class TimePicker extends Activity {

	private Button mStartButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_selector);	
				
		final NumberPicker nphr = (NumberPicker)findViewById(R.id.numberPickerHour);
		nphr.setMaxValue(23);
		nphr.setMinValue(0);
		nphr.setWrapSelectorWheel(true);
		
		final NumberPicker npmin1 = (NumberPicker)findViewById(R.id.numberPickerMinute1);
		npmin1.setMaxValue(59);
		npmin1.setMinValue(0);
		npmin1.setWrapSelectorWheel(true);
		
		mStartButton = (Button)findViewById(R.id.start_button);
		mStartButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String categoryFlag = getIntent().getExtras().getString("actionFlag");
				Intent i = new Intent(TimePicker.this, CountdownActivity.class);
				i.putExtra("hour", nphr.getValue());
				i.putExtra("minute", npmin1.getValue());
				i.putExtra("actionFlag", categoryFlag);
				startActivity(i);
				finish();
			}
		});
		
		
	}

}
