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
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PostsAdapterActivity extends Activity {

	private List<Post> myPostsList = new ArrayList<Post>();
	private List<JSONObject> myJSONObjectList = new ArrayList<JSONObject>();
	private List<String> myStringPostsList = new ArrayList<String>(); //new String[myJSONList.size()];
	private ArrayList<String> myReglement = new ArrayList<String>();
	private ArrayList<String> myContact = new ArrayList<String>();
	private ArrayList<String> myPartner = new ArrayList<String>();
	private ArrayList<ArrayList<String>> listImage  = new ArrayList<ArrayList<String>>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null){
			myStringPostsList = extras.getStringArrayList("myPostsList");
			myReglement  = extras.getStringArrayList("myReglement");
			myContact  = extras.getStringArrayList("myContact");
			myPartner  = extras.getStringArrayList("myPartner");
			listImage  = (ArrayList<ArrayList<String>>) extras.get("myImage");
			
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
		
		
		
		this.remplirPostsLists();
		ListView myListView = (ListView) findViewById(R.id.myListView);
		
		//PostBaseAdapter adapter = new PostBaseAdapter(this,myPostsList );
		PostAdapter adapter = new PostAdapter(this,myPostsList );
		
		myListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		myListView.setTextFilterEnabled(true);
	    myListView.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
	    		//Toast.makeText(getApplication(), ((TextView) view).getText(), Toast.LENGTH_LONG).show();
	    		//Toast.makeText(getApplication(), "id : "+id+" HELLO !! position: "+position, Toast.LENGTH_SHORT).show();
	    		try {
					Toast.makeText(getApplication(), "Actualité " + (position+1) + " : "+myJSONObjectList.get(position).get("slug").toString(), Toast.LENGTH_LONG).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		Intent i = new Intent(PostsAdapterActivity.this, OnePostActivity.class);
	    		i.putExtra("position", position);
	    		
	    		String[] myPostsList = new String[myStringPostsList.size()];
				
				for (int j=0; j < myStringPostsList.size(); j++){
					myPostsList[j] = myStringPostsList.get(j);
					//i.putExtra("myPostsList", myJSONList.get(j).toString());
				}
				
				List<String> list = new ArrayList<String>(myPostsList.length);  
				for (String s : myPostsList) {  
				    list.add(s);  
				} 
				
				
				i.putExtra("myPostsList", (Serializable) list );
				i.putExtra("myReglement", myReglement);
				i.putExtra("myContact", myContact);
				i.putExtra("myPartner", myPartner);	    		
				i.putExtra("myImage", (Serializable) listImage );
				
				startActivity(i);
	    	}
			
	    }); 
	    
	}

	private void remplirPostsLists() {
		// TODO Auto-generated method stub
		myPostsList.clear();
		
		for (int i = 0; i < myJSONObjectList.size(); i++){
			Post p;
			try {
				p = new Post( myJSONObjectList.get(i).get("title").toString(),
						myJSONObjectList.get(i).get("date").toString(), myJSONObjectList.get(i).get("slug").toString());
				myPostsList.add(p);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_post, menu);
		return true;
	}

}
