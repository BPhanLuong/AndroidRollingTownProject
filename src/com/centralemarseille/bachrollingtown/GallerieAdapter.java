package com.centralemarseille.bachrollingtown;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GallerieAdapter extends BaseAdapter {
	
	
	List<Photo> myPhotoList;
	// LayoutInflater aura pour mission de charger notre fichier XML
	LayoutInflater inflater;
	private Bitmap myPhoto;

		
	private class ViewHolder { 
		ImageView imageView;
		TextView ImageId;
		}
	
	public GallerieAdapter(Context context, List<Photo> objects) { 
		inflater = LayoutInflater.from(context);
		this.myPhotoList = objects;
	}
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			Log.v("test", "convertView is null");
			holder = new ViewHolder();			
			convertView = inflater.inflate(R.layout.photogallerie, null); 
			
			holder.imageView = (ImageView)convertView.findViewById(R.id.imageView1);
			holder.ImageId = (TextView)convertView.findViewById(R.id.ImageId);
			convertView.setTag(holder);
		
		} else {
			Log.v("test", "convertView is not null"); 
			holder = (ViewHolder) convertView.getTag();
		}
			
		Photo photo = myPhotoList.get(position); 
		holder.ImageId.setText("ImageId : "+photo.getId()); 
		
		String URL = photo.getThumbpath();
		Log.i("gallerie adapter thumbpath", photo.getId()+photo.getThumbpath() );			
		         
        
        // Create an object for subclass of AsyncTask
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { URL });
        holder.imageView.setImageBitmap(myPhoto);
	
		return convertView; 
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
        	myPhoto = result;
            
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
	
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return myPhotoList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return myPhotoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	

}