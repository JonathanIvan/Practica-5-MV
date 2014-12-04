package com.Cutonala.grabarvoz;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


@SuppressWarnings("unused")
public class GrabarActivity extends ActionBarActivity {

	private static final String LOG_TAG ="Grabadora";
	private MediaRecorder mediaRecorder;
	private MediaPlayer mediaPlayer;
	private static String fichero=Environment.getExternalStorageDirectory().getAbsolutePath() + "/rec/audio.3gp";
    private int numeroCancion=0; 
    private String strNumeroCancion=" ";
	private Button btnGrabar, btnDetener, btnReproducir, btnDetenerReproduccion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grabar_activity);
		
		Bundle bundle = getIntent().getExtras();
		strNumeroCancion = bundle.getString("nombreCancion");
		numeroCancion = Integer.parseInt(strNumeroCancion);
		btnGrabar = (Button) findViewById(R.id.btnGrabar) ;
		btnDetener = (Button) findViewById(R.id.btnDetener) ;
		btnReproducir = (Button) findViewById(R.id.btnReproducir);
		btnDetenerReproduccion = (Button) findViewById(R.id.btnDetenerReproduccion);
		btnDetener.setEnabled(false);
		btnReproducir.setEnabled(false);
		btnDetenerReproduccion.setEnabled(false);
	}
	
	public void grabar(View view) 
	{
		btnGrabar.setEnabled(false);
		btnDetener.setEnabled(true);
		btnReproducir.setEnabled(false);
		
		numeroCancion+=1;
		
		mediaRecorder = new MediaRecorder();
	    mediaRecorder.setOutputFile(fichero+"grabacion"+numeroCancion+".3gp");
	    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); 
	    try 
	    {
	        mediaRecorder.prepare();
	    } catch (IOException e) {
	        Log.e(LOG_TAG, "Fallo en grabaciacion");
	    }
	    mediaRecorder.start();
	}
	
	public void detenerGrabacion(View view) {
		btnGrabar.setEnabled(true);
		btnDetener.setEnabled(false);
		btnReproducir.setEnabled(true);
		
		mediaRecorder.stop();
		mediaRecorder.release();
	}
	
	public void reproducir(View view) {
		btnGrabar.setEnabled(false);
		btnDetenerReproduccion.setEnabled(true);
		btnReproducir.setEnabled(false);
		
		mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(fichero+"grabacion"+numeroCancion+".3gp");
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			Log.e (LOG_TAG,"Fallo la reproduccion");
		}
	}
	
	public void detener(View view) {
		try {
			btnDetenerReproduccion.setEnabled(false);
			btnReproducir.setEnabled(true);
			btnGrabar.setEnabled(true);
			
			mediaPlayer.stop();
		} catch (Exception e) {
			Log.e (LOG_TAG,"Fal1o la reproduccion");
		}
	}
}