package com.liquidware.serialmonitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.TextView;

public class serialMonitor extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
      
	setContentView(R.layout.main);
    final TextView tv = (TextView) findViewById(R.id.message);
    
	//Send button
	final Button button = (Button) findViewById(R.id.ok);
	button.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		
		// Perform action on click
		EditText et = (EditText) findViewById(R.id.entry);
		//et.setText("");
		
		tv.setText("> " + et.getText().toString() + "\n"); //tv.getText() + "234343 " + "\n34424" + "\n3434334");
		tv.setLines(14);
		
		//tv.setVerticalScrollBarEnabled(true);
		//tv.setScrollBarStyle(1);
		//tv.refreshDrawableState();
		//et.setText("> " + et.getText().toString() + "\n");	
	 }
	});
	
	//Clear Button
	final Button xxl = (Button) findViewById(R.id.xxl);
	xxl.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		
		// Perform action on click
		EditText et = (EditText) findViewById(R.id.entry);
		et.setText("");
		tv.setText("");
	  }
	});
  }
}