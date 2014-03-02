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
		
		strT.append("\n").append("Propri�t� intelectuelle : ").append("\n");
		strT.append("L'application mobile pr�sente d�nomm�e HappyRoller, con�ue dans un cadre p�dagogique, a pour but de r�alisation, la rediffusion de certaines donn�es du site internet www.rollingtown.com sous la forme d'une application mobile.").append("\n");		
		strT.append("Cette application utilise les donn�es en libre acc�s du site internet www.rollingtown.com, les droits sur ces donn�es reviennent donc aux propri�taires de ce dernier.").append("\n");
		strT.append("L'architecture g�n�rale de cette application ainsi que le cahier des charges des fonctionnalit�s ont �t� propos�s par Patrice Raby. La conception de l'application, l'impl�mentation des fonctions et des fonctionnalit�s ainsi que l'ergonomie sont la r�alisation de Bach Phan Luong.").append("\n");
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
