package com.liquidware.serialmonitor;

import android.util.*;
import android.content.Context;
import android.location.*;
import android.location.GpsStatus.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class serialMonitor extends Activity {
	private static final String TAG = "SerialMonitor";
	TextView tv;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//Text View
	    tv = (TextView) findViewById(R.id.message);
	    tv.setLines(14);
		
	    //Editext field
		final EditText et = (EditText) findViewById(R.id.entry);
		
		//Clear Button
		final Button cButton = (Button) findViewById(R.id.xxl);
		
		add_text("About to create lm");
		final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		add_text("Done creating lm");
		
		//Send Button
		final Button sButton = (Button) findViewById(R.id.ok);
		sButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				lm.SerialPrint("Hi Ardy!");
				//et.setText("");
			}
		});
		
		//Begin button
		final Button bButton = (Button) findViewById(R.id.begin);
		bButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				add_text("About to create ll");
				final LocationListener ll = new LocationListener(){
				       //sample listener...
					public void onLocationChanged(Location location) { }
					public void onStatusChanged(String provider, int status, Bundle extras) { }
					public void onProviderEnabled(String provider) { }
					public void onProviderDisabled(String provider) { }
				};
				

				
				add_text("Creating a SerialMsgListener");
				final GpsStatus.SerialMsgListener nl = new GpsStatus.SerialMsgListener() {
				   @Override
				   public void onSerialMsgReceived(String sMsg) {
				      /*
				       * Broadcast a message..
				       */
					   add_text(sMsg);
				   }
				};
				
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 50, 0, ll);
				lm.addSerialMsgListener(nl);
			 }
		

		});
	
	cButton.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		et.setText("");
		tv.setText("");
	  }
	});
	

  }
	
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