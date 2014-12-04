package com.Cutonala.grabarvoz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

@SuppressWarnings("unused")
public class MainActivity extends ActionBarActivity 
{
private static String directorio=Environment.getExternalStorageDirectory().getAbsolutePath() + "/rec/audio.3gp";
	private ListView listavGrabaciones;
	private List <String>  listasGrabaciones;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listavGrabaciones = (ListView) findViewById(R.id.listaGrabaciones);
		listasGrabaciones= new ArrayList<String>();
		
		consultarLista();
	}
	
	public void clicIrAGrabar(View v)
	{
		int numeroCancion = listasGrabaciones.size();
		Intent activityGrabar = new Intent(this, GrabarActivity.class);
		activityGrabar.putExtra("nombreCancion", numeroCancion+"");
	    startActivity(activityGrabar);
	}
	
	private void consultarLista() 
	{
		File archivo = new File(directorio);
		if(archivo.exists())
		{
			Filtro filtro = new Filtro(".3gp");
			
			if(archivo.listFiles(filtro).length > 0)
			{
				for(File cancion:archivo.listFiles(filtro))
				{
					listasGrabaciones.add(cancion.getName());
				}
				
				ArrayAdapter <String> list = new ArrayAdapter<String>(this, R.layout.text_list_view, listasGrabaciones);
				listavGrabaciones.setAdapter(list);
			}
		}
	}
}
