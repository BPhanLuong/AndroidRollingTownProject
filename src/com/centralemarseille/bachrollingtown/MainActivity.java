package com.centralemarseille.bachrollingtown;



import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import android.os.AsyncTask;



public class MainActivity extends Activity implements OnClickListener{

	static final String TAG ="MyApp";
	static final int request = 99;
	public static final String AGE = null;
	public static final int CHOOSE_BUTTON_REQUEST = 0;
	public static final String BUTTONS = "Boutons";
	
	private static final String URL = "http://www.rollingtown.com/wp-content/gallery/rando-hippy-seventies-7-mai-2010/rt046.jpg";
	private ProgressBar mProgressBar;
	
	private Context mainContext = this;
	
	private ImageView imageView;
	 
	
	private List<JSONObject> myJSONList = new ArrayList<JSONObject>();
	private JSONObject jsonObjectPosts = null;
	private List<String> listPost = new ArrayList<String>();
	
	private JSONObject jsonObjectReglement = null;
	private List<String> listReglement = new ArrayList<String>();
	
	private JSONObject jsonObjectContact = null;
	private List<String> listContact = new ArrayList<String>();
	
	private JSONObject jsonObjectPartenaire = null;
	private List<String> listPartner = new ArrayList<String>();
	
	private List<JSONObject> myJSONObjectsList = new ArrayList<JSONObject>();
	
	private JSONObject jsonObjectImage = null;	
	
	private JSONArray jsonArrayImage = null;
	
	
	private List<List<JSONObject>> myJSONArrayImageAlbum = new ArrayList<List<JSONObject>>();
	private List<List<String>> listImageAlbum = new ArrayList<List<String>>();
	

	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
                
        Bundle extras = getIntent().getExtras();
        
        if (extras != null){
			listPost = extras.getStringArrayList("myPostsList");
			listReglement = extras.getStringArrayList("myReglement");
			listContact = extras.getStringArrayList("myContact");
			listPartner = extras.getStringArrayList("myPartner");
			listImageAlbum = (List<List<String>>) extras.get("myImage");

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
       
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar1);
        
        imageView = (ImageView)findViewById(R.id.imageView1);          
        
        // Create an object for subclass of AsyncTask
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { URL });
        
        
    }
      
         
       	
	//@Override
	public void onClick(View v) {
		
		// TODO Auto-generated method stub
		if (v.getId() == R.id.button2){
			Intent i = new Intent(MainActivity.this, PostsAdapterActivity.class);			
			i.putExtra("myPostsList", (Serializable) listPost );			
			i.putExtra("myReglement", (Serializable) listReglement );
			i.putExtra("myContact", (Serializable) listContact );
			i.putExtra("myPartner", (Serializable) listPartner );
			i.putExtra("myImage", (Serializable) listImageAlbum );
			
			startActivity(i);
			
		}
    	    	
    	if (v.getId() == R.id.button1){
    					
			LoadItemsTask lit = new LoadItemsTask();
											
			lit.execute();
    	}
    	
    	
    	
    	if (v.getId() == R.id.button3){
    		Intent i = new Intent(MainActivity.this, AlbumActivity.class);    		
			i.putExtra("myImage", (Serializable) listImageAlbum ); 
			
			startActivity(i); 
    	}
    	
    	if (v.getId() == R.id.button4){
    		Intent i = new Intent(MainActivity.this, ReglementActivity.class);    		
			i.putExtra("myReglement", (Serializable) listReglement ); 
			
			startActivity(i);    		
    	}
    	
    	if (v.getId() == R.id.button5){
    		Intent i = new Intent(MainActivity.this, PartnerActivity.class);    		
			i.putExtra("myPartner", (Serializable) listPartner ); 
			
			startActivity(i);    		
    	}
    	
    	if (v.getId() == R.id.button6){
    		Intent i = new Intent(MainActivity.this, ContactActivity.class);    		
			i.putExtra("myContact", (Serializable) listContact ); 
			
			startActivity(i);    		
    	}
    	
    	if (v.getId() == R.id.button7){
    		Intent i = new Intent(MainActivity.this, ReadMeActivity.class);			
			
			startActivity(i);    		
    	}

	
	}
	



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
    private class GetXMLTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }
 
        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
 
        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
 
            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.
                        decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }
 
        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
 
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }
    



	private class LoadItemsTask extends AsyncTask<URL, Integer, List<JSONObject> > {
		
		private int progress = 5;
		
		@Override
		protected void onPreExecute(){
			Toast.makeText(mainContext, "Begin retrieving data. Please wait ...", Toast.LENGTH_SHORT).show();
			
		}
		
		@Override
		protected void onProgressUpdate(Integer... values){
			super.onProgressUpdate(values);
			
			mProgressBar.setProgress(values[0]);
		}
	
		@Override
		protected List<JSONObject> doInBackground(URL... params) {
			// TODO Auto-generated method stub
			publishProgress(progress);
			
			HttpClient httpclient = new DefaultHttpClient();
			
			HttpGet httpgetPosts = new HttpGet("http://www.rollingtown.com/?json=get_recent_posts");
			HttpGet httpgetReglement = new HttpGet("http://www.rollingtown.com/reglement/?json=get_page");
			HttpGet httpgetContact = new HttpGet("http://www.rollingtown.com/contact/?json=get_page");
			HttpGet httpgetPartenaire = new HttpGet("http://www.rollingtown.com/nos-partenaires/?json=get_page");
			
			HttpGet httpgetImage = new HttpGet("http://www.rollingtown.com/api/galeries/listall/");
			
			
			progress = 10;
			publishProgress(progress);

			
									
			try {
				HttpResponse responsePosts = httpclient.execute(httpgetPosts);			
								
				progress = 30;
				publishProgress(progress);				
								
				String linePosts = "";										
				InputStream inputstreamPosts = responsePosts.getEntity().getContent();					
				linePosts = convertStreamToString(inputstreamPosts);					
				jsonObjectPosts = new JSONObject(linePosts);					
				myJSONObjectsList.add(jsonObjectPosts);
				
				progress += 5;
				publishProgress(progress);
				
				
				HttpResponse responseReglement = httpclient.execute(httpgetReglement);
				String lineReglement = "";					
				InputStream inputstreamReglement = responseReglement.getEntity().getContent();										
				lineReglement = convertStreamToString(inputstreamReglement);										
				jsonObjectReglement = new JSONObject(lineReglement);					
				myJSONObjectsList.add(jsonObjectReglement);
				
				
				HttpResponse responseContact = httpclient.execute(httpgetContact);				
				String lineContact = "";					
				InputStream inputstreamContact = responseContact.getEntity().getContent();					
				lineContact = convertStreamToString(inputstreamContact);					
				jsonObjectContact = new JSONObject(lineContact);					
				myJSONObjectsList.add(jsonObjectContact);
				
				
				progress += 5;
				publishProgress(progress);
				
				
				HttpResponse responsePartenaire = httpclient.execute(httpgetPartenaire);
				String linePartenaire = "";					
				InputStream inputstreamPartenaire = responsePartenaire.getEntity().getContent();					
				linePartenaire = convertStreamToString(inputstreamPartenaire);					
				jsonObjectPartenaire = new JSONObject(linePartenaire);					
				myJSONObjectsList.add(jsonObjectPartenaire);
				
				progress += 5;
				publishProgress(progress);
				
				HttpResponse responseImage = httpclient.execute(httpgetImage);
				String lineImage = "";					
				InputStream inputstreamImage = responseImage.getEntity().getContent();					
				lineImage = convertStreamToString(inputstreamImage);					
				jsonObjectImage = new JSONObject(lineImage);
				myJSONObjectsList.add(jsonObjectImage);
				
				
				progress += 5;
				publishProgress(progress);	
										
				
			} catch (ClientProtocolException e) {
				Toast.makeText(mainContext, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
				Log.e("test",e.getMessage()); 
			} catch (IOException e) {
				Toast.makeText(mainContext, "Caught IOException", Toast.LENGTH_SHORT).show();
				Log.e("test",e.getMessage());
			} 
			catch (Exception e) {
				//Toast.makeText(mainContext, "Caught Exception", Toast.LENGTH_SHORT).show();
				
				Log.e("test",""+e.toString()+"-"+e.getMessage()); 
				
				}
			
						
			return myJSONObjectsList;
		}
		
				    
	
		private String convertStreamToString(InputStream inputstream) {
			String line = "";
			StringBuilder total = new StringBuilder();
			BufferedReader rd = new BufferedReader(new InputStreamReader(inputstream));
			try {
				while ((line = rd.readLine()) != null) 
				{ 
					total.append(line);
					
					progress += 5;
					publishProgress(progress);
				}
				rd.close();
				
			} catch (Exception e) {
				Toast.makeText(mainContext, "Stream Exception", Toast.LENGTH_SHORT).show();
				}
			return total.toString();		
		}
		
		
		protected void onPostExecute(List<JSONObject> jsonRootList){
					
			myJSONList = parsePost(jsonRootList.get(0));
			
			for (int i=0; i < 8; i++){
				myJSONArrayImageAlbum.add(parseImage(jsonRootList.get(4),i)); 
			}			
			
			
			jsonObjectReglement = parseAutre(jsonRootList.get(1));
			jsonObjectContact = parseAutre(jsonRootList.get(2));
			jsonObjectPartenaire = parseAutre(jsonRootList.get(3));
			

			String[] myPostsList = new String[myJSONList.size()];
			
			for (int j=0; j < myJSONList.size(); j++){
				myPostsList[j] = myJSONList.get(j).toString();				
			}
			
			listPost = new ArrayList<String>(myPostsList.length);  
			for (String s : myPostsList) {  
			    listPost.add(s);  
			}
			
			
			for (int k=0; k < 8; k++){
				String[] myImageList = new String[myJSONArrayImageAlbum.get(k).size()];
				
				listImageAlbum.add(new ArrayList<String>());
				
				
				for (int j=0; j < myJSONArrayImageAlbum.get(k).size(); j++){
					myImageList[j] = myJSONArrayImageAlbum.get(k).get(j).toString();				
				}
				
				//listImageAlbum.get(k) = new ArrayList<String>(myImageList.length);  
				for (String s : myImageList) {  
					
				    listImageAlbum.get(k).add(s);
				}
								
			}
			
			
			
			int taille = 4;
			String[] myReglement = new String[taille];
			String[] myContact = new String[taille];
			String[] myPartner = new String[taille];
			
			
			try {
					
				myReglement[0] = jsonObjectReglement.getString("content");
				myReglement[1] = jsonObjectReglement.getString("excerpt");
				myReglement[2] = jsonObjectReglement.getString("date");
				myReglement[3] = jsonObjectReglement.getString("modified");
				
				myContact[0] = jsonObjectContact.getString("content");
				myContact[1] = jsonObjectContact.getString("excerpt");
				myContact[2] = jsonObjectContact.getString("date");
				myContact[3] = jsonObjectContact.getString("modified");
				
				myPartner[0] = jsonObjectPartenaire.getString("content");
				myPartner[1] = jsonObjectPartenaire.getString("excerpt");
				myPartner[2] = jsonObjectPartenaire.getString("date");
				myPartner[3] = jsonObjectPartenaire.getString("modified");
			
			
			} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();				
				
			}
			
			
			listReglement = new ArrayList<String>(myReglement.length);  
			for (String s : myReglement) {  
			    listReglement.add(s);  
			} 
			
			listContact = new ArrayList<String>(myContact.length);  
			for (String s : myContact) {  
			    listContact.add(s);  
			}
			
			listPartner = new ArrayList<String>(myPartner.length);  
			for (String s : myPartner) {  
			    listPartner.add(s);  
			}
			
			
			progress += 10;		
			
			Toast.makeText(mainContext, "Finish retrieving data. ", Toast.LENGTH_SHORT).show();
						
		}
	
		private List<JSONObject> parsePost(JSONObject jsonRoot){
			
			JSONArray jsonArray = null;
			try {
				jsonArray = jsonRoot.getJSONArray("posts");
				progress += 5;
				publishProgress(progress);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// TODO Auto-generated method stub
			List<JSONObject> myJSONList = new ArrayList<JSONObject>();
			for (int i = 0; i<jsonArray.length(); i++){
				try {
					
					myJSONList.add(jsonArray.getJSONObject(i));
										
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				progress += 5;
				publishProgress(progress);
			}
			return myJSONList;
		}
		
		
		private JSONObject parseAutre(JSONObject jsonRoot){
			JSONObject jsonObjectAutre = null;			
			try {
				jsonObjectAutre = jsonRoot.getJSONObject("page");
				progress += 5;
				publishProgress(progress);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return jsonObjectAutre;
		}
		
		
		private List<JSONObject> parseImage(JSONObject jsonRoot, int j){
			
			try {
				jsonObjectImage = jsonRoot.getJSONObject(""+j);				
				progress += 5;
				publishProgress(progress);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			try {
				jsonArrayImage = jsonObjectImage.getJSONArray("data");
				progress += 5;
				publishProgress(progress);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// TODO Auto-generated method stub
			List<JSONObject> myJSONList = new ArrayList<JSONObject>();
			for (int i = 0; i<jsonArrayImage.length(); i++){
				try {
					
					myJSONList.add(jsonArrayImage.getJSONObject(i));
										
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				progress += 5;
				publishProgress(progress);
			}
			return myJSONList;
		}

	}
    
}

