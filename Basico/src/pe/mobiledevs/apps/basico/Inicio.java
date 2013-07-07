package pe.mobiledevs.apps.basico;

import java.util.ArrayList;
import java.util.List;

import pe.mobiledevs.apps.basico.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Inicio extends Activity implements OnItemClickListener {

	private ListView listaMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_inicio);

		listaMenu = (ListView) findViewById(R.id.listaMenu);

		List<String> opciones = new ArrayList<String>();
		opciones.add("Texto");
		opciones.add("Botones");
		opciones.add("Cajas de Texto");
		opciones.add("Radio Button");
		opciones.add("Spinner");
		opciones.add("Menu");

		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, opciones);

		listaMenu.setAdapter(adaptador);
		listaMenu.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(this, Textos.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, Botones.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(this, CajaTexto.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(this, Radio.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(this, MiSpinner.class);
			startActivity(intent);
			break;
		case 5:
			intent = new Intent(this, MenuSimple.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inicio, menu);
		return true;
	}

}
