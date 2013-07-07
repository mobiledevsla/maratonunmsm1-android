package pe.mobiledevs.apps.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Inicio extends Activity {

	ProgressDialog dialogoEspera = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_inicio);

		lecturaEnOtroHilo();

	}

	private void simpleLectura() {

		HttpClient cliente = new DefaultHttpClient();
		HttpGet peticionGet = new HttpGet("http://javapetit.net/sport");

		try {
			HttpResponse respuesta = cliente.execute(peticionGet);
			HttpEntity entidad = respuesta.getEntity();
			String respuestaComoCadena = EntityUtils.toString(entidad);
			mostrarEnLista(respuestaComoCadena);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void lecturaEnOtroHilo() {

		dialogoEspera = new ProgressDialog(this);
		dialogoEspera.setIndeterminate(true);
		dialogoEspera.setTitle("Cargando datos");
		dialogoEspera.setMessage("Por favor espere...");
		dialogoEspera.show();

		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				simpleLectura();
			}
		});
		hilo.start();

	}

	private void mostrarEnLista(final String cadena) {

		Inicio.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					ListView lista = (ListView) findViewById(R.id.lista);
					List<String> nombres = new ArrayList<String>();

					JSONArray objetoJsonArray = new JSONArray(cadena);

					for (int i = 0; i < objetoJsonArray.length(); i++) {
						nombres.add(objetoJsonArray.getString(i));
					}

					ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
							Inicio.this, android.R.layout.simple_list_item_1,
							nombres);
					lista.setAdapter(adaptador);

					dialogoEspera.dismiss();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true;
	}

}
