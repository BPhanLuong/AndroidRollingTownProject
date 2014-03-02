package com.centralemarseille.bachrollingtown;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.widget.TextView;

public class ReadMeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_me);
		
		TextView myText = (TextView) findViewById(R.id.textView1);
		
		StringBuilder strT = new StringBuilder("");
		
		strT.append("\n").append("Propriété intelectuelle : ").append("\n");
		strT.append("L'application mobile présente dénommée HappyRoller, conçue dans un cadre pédagogique, a pour but de réalisation, la rediffusion de certaines données du site internet www.rollingtown.com sous la forme d'une application mobile.").append("\n");		
		strT.append("Cette application utilise les données en libre accès du site internet www.rollingtown.com, les droits sur ces données reviennent donc aux propriétaires de ce dernier.").append("\n");
		strT.append("L'architecture générale de cette application ainsi que le cahier des charges des fonctionnalités ont été proposés par Patrice Raby. La conception de l'application, l'implémentation des fonctions et des fonctionnalités ainsi que l'ergonomie sont la réalisation de Bach Phan Luong.").append("\n");
		strT.append("\n");
		
		
		myText.setText(strT.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_me, menu);
		return true;
	}

}
