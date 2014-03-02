package com.centralemarseille.bachrollingtown;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class OnePostActivity extends Activity implements OnClickListener{
	
	//private List<Post> myPostsList = new ArrayList<Post>();
	private List<JSONObject> myJSONObjectList = new ArrayList<JSONObject>();
	private List<String> myStringPostsList = new ArrayList<String>();
	private int position;
	private ArrayList<String> myReglement = new ArrayList<String>();
	private ArrayList<String> myContact = new ArrayList<String>();
	private ArrayList<String> myPartner = new ArrayList<String>();
	private final String mimeType = "text/html";
    private final String encoding = "utf-8";
	private ArrayList<ArrayList<String>> listImage = new ArrayList<ArrayList<String>>();
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onepost);
		
		Bundle extras = getIntent().getExtras();
		
		
		if (extras != null){
			position = extras.getInt("position");
			myStringPostsList = extras.getStringArrayList("myPostsList");
			myReglement   = extras.getStringArrayList("myReglement");
			myContact  = extras.getStringArrayList("myContact");
			myPartner  = extras.getStringArrayList("myPartner");
			listImage = (ArrayList<ArrayList<String>>) extras.get("myImage");
			
			
			
			for (int i=0; i < myStringPostsList.size();i++){
				String u = myStringPostsList.get(i);
				JSONObject v = null;
				try {
					v = new JSONObject(u);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				myJSONObjectList.add( v );
			}
		}
		
		
		
		TextView myTitle = (TextView) findViewById(R.id.title);
		TextView myDate = (TextView) findViewById(R.id.date);
		TextView mySlug = (TextView) findViewById(R.id.slug);
		WebView myContent = (WebView) findViewById(R.id.content);
		
		JSONObject u = myJSONObjectList.get(position);
		
		
		StringBuilder strT = new StringBuilder("");		
		StringBuilder strD = new StringBuilder("");
		StringBuilder strS = new StringBuilder("");	
		StringBuilder strC = new StringBuilder("");
		
		try {
			int numero = position +1;
			strT.append("\n").append("Titre : ").append("\n").append(u.getString("title"));
			strT.append("\n").append("Actualité n° : "+numero);
			myTitle.setText(strT.toString());
			
			strD.append("\n").append("Date : ").append("\n").append(u.getString("date"));
			myDate.setText(strD.toString());
			
			strS.append("\n").append("Mot clé : ").append("\n").append(u.getString("slug"));
			mySlug.setText(strS.toString());
			
			strC.append("\n").append("Information principale : ").append(u.getString("content"));
			myContent.loadData(strC.toString(), mimeType, encoding);
			myContent.setBackgroundColor(0);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
	}
	
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		//i.putExtra("position", position);
		
		if (v.getId() == R.id.button1){
			Intent pintent = new Intent(OnePostActivity.this, OnePostActivity.class);
			pintent.putExtra("myPostsList", (Serializable) myStringPostsList );
			pintent.putExtra("myReglement", myReglement);
			pintent.putExtra("myContact", myContact);
			pintent.putExtra("myPartner", myPartner);
			pintent.putExtra("myImage", (Serializable) listImage );
			
			pintent.putExtra("position", 0);
			
			startActivity(pintent);
			
		}
		
		if (v.getId() == R.id.button4){
			Intent pintent = new Intent(OnePostActivity.this, OnePostActivity.class);
			pintent.putExtra("myPostsList", (Serializable) myStringPostsList );
			pintent.putExtra("myReglement", myReglement);
			pintent.putExtra("myContact", myContact);
			pintent.putExtra("myPartner", myPartner);
			pintent.putExtra("myImage", (Serializable) listImage );
			
			pintent.putExtra("position", myStringPostsList.size()-1);
			
			startActivity(pintent);
			
		}
		
		if (v.getId() == R.id.button2){
			Intent pintent = new Intent(OnePostActivity.this, OnePostActivity.class);
			pintent.putExtra("myPostsList", (Serializable) myStringPostsList );
			pintent.putExtra("myReglement", myReglement);
			pintent.putExtra("myContact", myContact);
			pintent.putExtra("myPartner", myPartner);
			pintent.putExtra("myImage", (Serializable) listImage );
			
			if (position == 0){
				pintent.putExtra("position", position);
			}
			else{
				pintent.putExtra("position", position-1);
			}
						
			startActivity(pintent);			
		}
		
		if (v.getId() == R.id.button3){
			Intent pintent = new Intent(OnePostActivity.this, OnePostActivity.class);
			pintent.putExtra("myPostsList", (Serializable) myStringPostsList );
			pintent.putExtra("myReglement", myReglement);
			pintent.putExtra("myContact", myContact);
			pintent.putExtra("myPartner", myPartner);
			pintent.putExtra("myImage", (Serializable) listImage );
			
			if (position == myStringPostsList.size()-1){
				pintent.putExtra("position", position);
			}
			else{
				pintent.putExtra("position", position+1);
			}
						
			startActivity(pintent);			
		}
		
		if (v.getId() == R.id.button5){
			Intent mainMenu = new Intent(OnePostActivity.this, MainActivity.class);
			mainMenu.putExtra("myPostsList", (Serializable) myStringPostsList );
			mainMenu.putExtra("myReglement", myReglement);
			mainMenu.putExtra("myContact", myContact);
			mainMenu.putExtra("myPartner", myPartner);
			mainMenu.putExtra("myImage", (Serializable) listImage );
			
			
			startActivity(mainMenu);
			
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.one_post, menu);
		return true;
	}

}
