package com.centralemarseille.bachrollingtown;



import java.util.List;
import android.content.Context; 
//import android.util.Log;
import android.view.LayoutInflater; 
import android.view.View;
import android.view.ViewGroup; 
import android.widget.BaseAdapter; 
import android.widget.TextView;

/* On surcharge ArrayAdapter */

public class PostAdapter extends BaseAdapter { 
	List<Post> myPostsList;
// LayoutInflater aura pour mission de charger notre fichier XML
	LayoutInflater inflater;
/**
* Elle nous servira aÌ€ meÌ�moriser les eÌ�leÌ�ments de la liste en meÌ�moire pour 
* * quâ€™aÌ€ chaque rafraichissement lâ€™eÌ�cran ne scintille pas
	*
	* @author patrice
*/
	
	private class ViewHolder { 
		TextView Title;
		TextView Date;
		TextView Slug;
		}
	
	public PostAdapter(Context context, List<Post> objects) { 
		inflater = LayoutInflater.from(context);
		this.myPostsList = objects;
	}
	
	/**
	* GeÌ�neÌ€re la vue pour un objet */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) { 
		ViewHolder holder;
		if (convertView == null) {
			//Log.v("test", "convertView is null");
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.post_item, null); 
			holder.Title = (TextView) convertView.findViewById(R.id.txtTitle); 
			holder.Date = (TextView) convertView.findViewById(R.id.txtDate); 
			holder.Slug = (TextView) convertView.findViewById(R.id.txtSlug);
			convertView.setTag(holder);
		
	} else {
		//Log.v("test", "convertView is not null"); 
		holder = (ViewHolder) convertView.getTag();
	}
		
	Post post = myPostsList.get(position); 
	holder.Title.setText("Titre : "+post.getTitle()); 
	holder.Date.setText("Date : "+post.getDate());
	holder.Slug.setText("Mot clé : "+post.getSlug());
	
		return convertView; 
	}
	/**
	* Retourne le nombre d'eÌ�leÌ�ments */
	@Override
	public int getCount() {
	// TODO Auto-generated method stub 
		return myPostsList.size();
		
	}
	/**
	* Retourne l'item aÌ€ la position */
	@Override
	public Post getItem(int position) { // TODO Auto-generated method stub 
		return myPostsList.get(position);
	}
	/**
	* Retourne la position de l'item */
	@Override
	public long getItemId(int position) { // TODO Auto-generated method stub 
		return position;
	}
	}