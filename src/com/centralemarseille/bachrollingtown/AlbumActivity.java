package com.centralemarseille.bachrollingtown;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlbumActivity extends Activity implements OnClickListener{
	private List<List<String>> myStringImageListAlbum = new ArrayList<List<String>>();
	private List<List<JSONObject>> myJSONObjectImageList =  new ArrayList<List<JSONObject>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			myStringImageListAlbum  = (List<List<String>>) extras.get("myImage");		
					
		}
		
		Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button)findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button)findViewById(R.id.button4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button)findViewById(R.id.button5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button)findViewById(R.id.button6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button)findViewById(R.id.button7);
        btn7.setOnClickListener(this);
        Button btn8 = (Button)findViewById(R.id.button8);
        btn8.setOnClickListener(this);
        
	}
	
	@Override
	public void onClick(View v) {
		int[] RIDButton = new int[8];
		RIDButton[0] = R.id.button1;
		RIDButton[1] = R.id.button2;
		RIDButton[2] = R.id.button3;
		RIDButton[3] = R.id.button4;
		RIDButton[4] = R.id.button5;
		RIDButton[5] = R.id.button6;
		RIDButton[6] = R.id.button7;
		RIDButton[7] = R.id.button8;
		
		for (int k=0; k < 8; k++){
			if (v.getId() == RIDButton[k]){
	    		Intent i = new Intent(AlbumActivity.this, PhotoAdapterActivity.class);    		
				i.putExtra("myImage", (Serializable) myStringImageListAlbum.get(k) ); 
				i.putExtra("numeroAlbum", ""+(k+1));
				
				startActivity(i); 
	    	}
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.album, menu);
		return true;
	}

}
