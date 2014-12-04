package com.Cutonala.grabarvoz;

import java.io.File;
import java.io.FilenameFilter;

import android.util.Log;

public class Filtro implements FilenameFilter
{
   String formatoBuscado;
	
	Filtro(String formato)
	{
		this.formatoBuscado = formato;
		Log.i("FORMATO: ",formato);
	}
	
	@Override
	public boolean accept(File directorio, String nombreArchivo)
	{
		// TODO Auto-generated method stub
		return nombreArchivo.endsWith(formatoBuscado);
	}
}