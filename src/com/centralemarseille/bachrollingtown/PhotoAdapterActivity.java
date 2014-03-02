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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PhotoAdapterActivity extends Activity implements OnClickListener{
	private List<Photo> myImageList = new ArrayList<Photo>();
	private List<String> myStringImageList = new ArrayList<String>();
	private List<JSONObject> myJSONObjectImageList = new ArrayList<JSONObject>();
	private String numeroAlbum;
	//private List<List<String>> myStringImageListAlbum =  new ArrayList<List<String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_adapter);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			myStringImageList  = extras.getStringArrayList("myImage");
			numeroAlbum = extras.getString("numeroAlbum");
					
			
			for (int i=0; i < myStringImageList.size();i++){
				String u = myStringImageList.get(i);
				JSONObject v = null;
				try {
					v = new JSONObject(u);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				myJSONObjectImageList.add( v );
			}
		}
		
		Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
		
		
		
		this.remplirImageLists();
		ListView myListView = (ListView) findViewById(R.id.listView1);
		TextView myTextView = (TextView)findViewById(R.id.textView2);
		myTextView.setText("Vous regardez l'album "+numeroAlbum);
		
		PhotoAdapter adapter = new PhotoAdapter(this, myImageList);
		
		myListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
				
		myListView.setTextFilterEnabled(true);
	    myListView.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	    		
	    		try {
	    			int numero = position+1;
					Toast.makeText(getApplication(), "Image "+numero+" , Path : "+myJSONObjectImageList.get(position).get("path").toString(), Toast.LENGTH_LONG).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		Intent i = new Intent(PhotoAdapterActivity.this, OnePhotoActivity.class);
	    		
	    		
	    		try {
	    			
					i.putExtra("myPhotoPath", myJSONObjectImageList.get(position).get("path").toString() );
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				startActivity(i);
	    	}
			
	    }); 
	}
	
	private void remplirImageLists() {
		// TODO Auto-generated method stub
		myImageList.clear();
		
		for (int i = 0; i < myJSONObjectImageList.size(); i++){
			Photo p;
			try {
				p = new Photo( myJSONObjectImageList.get(i).get("id").toString(),
						myJSONObjectImageList.get(i).get("path").toString(), myJSONObjectImageList.get(i).get("thumbpath").toString() );
				myImageList.add(p);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_adapter, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1){
    		Intent i = new Intent(PhotoAdapterActivity.this, GallerieActivity.class);    		
			i.putExtra("myImage", (Serializable) myStringImageList ); 
			i.putExtra("numeroAlbum",numeroAlbum);
			
			startActivity(i); 
    	}
		
	}

}
