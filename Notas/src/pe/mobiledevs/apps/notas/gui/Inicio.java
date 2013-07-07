package pe.mobiledevs.apps.notas.gui;

import java.util.ArrayList;
import java.util.List;

import pe.mobiledevs.apps.notas.R;
import pe.mobiledevs.apps.notas.gui.adaptadores.AdaptadorNota;
import pe.mobiledevs.apps.notas.modelo.entidades.Nota;
import pe.mobiledevs.apps.notas.modelo.proveedores.NotasOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Inicio extends Activity implements OnItemClickListener {
	private ListView menuLista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inicio);
		leerNotas();
	}

	private void leerNotas() {
		menuLista = (ListView) findViewById(R.id.menuLista);

		NotasOpenHelper dbHelper = new NotasOpenHelper(this);

		List<Nota> notas = new ArrayList<Nota>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("Nota", new String[] { "_id", "Titulo",
				"Contenido" }, null, null, null, null, null);

		while (cursor.moveToNext()) {
			Nota nota = new Nota();

			nota.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			nota.setTitulo(cursor.getString(cursor.getColumnIndex("Titulo")));
			nota.setContenido(cursor.getString(cursor
					.getColumnIndex("Contenido")));

			notas.add(nota);
		}

		cursor.close();
		db.close();

		dbHelper.close();

		AdaptadorNota adaptadorNota = new AdaptadorNota(this, notas);

		menuLista.setAdapter(adaptadorNota);
		menuLista.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_inicio, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.accion_nuevo:

			Intent intent = new Intent(this, NotaDetalle.class);
			startActivityForResult(intent, 100);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		Bundle extras = new Bundle();
		extras.putLong("identificador", id);

		Intent intent = new Intent(this, NotaDetalle.class);
		intent.putExtras(extras);

		startActivityForResult(intent, 100);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		leerNotas();

	}

}
