package pe.mobiledevs.apps.basico;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MiSpinner extends Activity {

	private Spinner listaSpinner = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actividad_spinner);

		List<String> opciones = new ArrayList<String>();
		opciones.add("Opcion 1");
		opciones.add("Opcion 2");
		opciones.add("Opcion 3");

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, opciones);

		listaSpinner = (Spinner) findViewById(R.id.listaSpinner);
		listaSpinner.setAdapter(adaptador);

		listaSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View vistaSeleccionada, int posicion, long id) {
						Toast.makeText(
								MiSpinner.this,
								"Seleccionaste"
										+ parent.getItemAtPosition(posicion)
												.toString(), Toast.LENGTH_SHORT)
								.show();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

}
