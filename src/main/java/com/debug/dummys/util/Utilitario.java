package com.debug.dummys.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Utilitario {

	public static String obtenerStringFromFile(final String nombre) {
		StringBuffer sb = new StringBuffer();
		try {
			final Resource jsonArchivo = new ClassPathResource(nombre + ".txt");
			DataInputStream in = new DataInputStream(jsonArchivo.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				sb.append(strLine);
			}
			br.close();
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("IO Error:" + e.getMessage());
		}
		catch (final IOException e) {
			e.printStackTrace();
			System.out.println("Parse Error:" + e.getMessage());
		}

		return sb.toString();
	}
	
	public Object obtenerJson(final String nombre) {
		final JSONParser parser = new JSONParser();
		Object obj = null;

		try {

			final Resource jsonArchivo = new ClassPathResource("json/" + nombre + ".json");
			final File archivo = jsonArchivo.getFile();

			obj = parser.parse(new FileReader(archivo));

		}
		catch (final IOException e) {
			System.out.println("IO Error:" + e.getMessage());
		}
		catch (final ParseException e) {
			System.out.println("Parse Error:" + e.getMessage());
		}

		return obj;
	}
}
