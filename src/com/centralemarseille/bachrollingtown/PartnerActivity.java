package com.centralemarseille.bachrollingtown;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

public class PartnerActivity extends Activity {

	private ArrayList<String> myPartner = new ArrayList<String>();
	// les définitions de type mime et de l'encodage
    private final String mimeType = "text/html";
    private final String encoding = "utf-8";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partner);
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null){
			myPartner = extras.getStringArrayList("myPartner");			
		}
		
		
		
		TextView myModified = (TextView) findViewById(R.id.modified);
		TextView myDate = (TextView) findViewById(R.id.date);
		WebView myExcerpt = (WebView) findViewById(R.id.excerpt);
		WebView myContent = (WebView) findViewById(R.id.content);
		
		
		
		StringBuilder strM = new StringBuilder("");		
		StringBuilder strD = new StringBuilder("");
		StringBuilder strE = new StringBuilder("");	
		StringBuilder strC = new StringBuilder("");
		
		strM.append("\n").append("Dernière Modification : ").append("\n").append(myPartner.get(3)).append("\n");			
		myModified.setText(strM.toString());
		
		strD.append("\n").append("Date : ").append(myPartner.get(2)).append("\n");
		myDate.setText(strD.toString());
		
		strE.append("\n").append("En bref : ").append(myPartner.get(1));
		myExcerpt.loadData(strE.toString(), mimeType, encoding);
		myExcerpt.setBackgroundColor(0);
		
		strC.append("\n").append("Information principale : ").append(myPartner.get(0));
		myContent.loadData(strC.toString(), mimeType, encoding);
		myContent.setBackgroundColor(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.partner, menu);
		return true;
	}

}
