package com.liquidware.serialmonitor;

import android.util.*;
import android.content.Context;
import android.serial.*;
import android.serial.SerialStatus.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class serialMonitor extends Activity {
	private static final String TAG = "SerialMonitor";
	TextView tv;
	boolean enabled = false;
	int baud = 57600;
	String device = "ttyUSB0";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final SerialManager Serial = (SerialManager) getSystemService(Context.SERIAL_SERVICE);
		
		//Text View
	    tv = (TextView) findViewById(R.id.message);
	    tv.setLines(14);
		
	    //Editext field
		final EditText et = (EditText) findViewById(R.id.entry);
		
		//Clear Button
		final Button cButton = (Button) findViewById(R.id.xxl);
		cButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				et.setText("");
				tv.setText("");
			  }
			});
		
		//Send Button
		final Button sButton = (Button) findViewById(R.id.ok);
		sButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String msg = et.getText().toString();
				
				Serial.print(msg); //send the data in the message box
			}
		});
		
		//Begin button
		final Button bButton = (Button) findViewById(R.id.begin);
		bButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (enabled) {
					/* Disable */
					add_text("Ending Serial");
					Serial.end(device);
					bButton.setText("Begin");
					enabled = false;
					return;
				}
				
				/* Begin */
				if (Serial.begin(device, baud)) {
					add_text("Beginning Serial @" + baud + " baud");
					enabled = true;
					add_text("Ready");
					bButton.setText("End");
				} else {
					add_text("Error: Could not start serial.");
				}
			 }
		});
	
	
	
	/* Handle Serial messages */
	final SerialStatus.SerialMsgListener sl = new SerialStatus.SerialMsgListener() {
		   public void onSerialMsgReceived(String sMsg) {
		      /*
		       * Handle the message..
		       */
			   add_text(sMsg);
		   }
		};
	Serial.addSerialMsgListener(sl);

  } /* on create */
	
	/**
	 * Utility
	 */
	int line_count = 0;
	private void add_text(String s) {
		if (line_count > 10) {
			tv.setText("");
			line_count = 0;
		}
		tv.setText(tv.getText() + "\n>" + s);
		line_count++;
	}	
	
}