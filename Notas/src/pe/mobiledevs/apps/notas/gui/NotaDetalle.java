package pe.mobiledevs.apps.notas.gui;

import pe.mobiledevs.apps.notas.R;
import pe.mobiledevs.apps.notas.modelo.entidades.Nota;
import pe.mobiledevs.apps.notas.modelo.proveedores.NotasOpenHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NotaDetalle extends Activity {

	private long idNota = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nota_detalle);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			idNota = extras.getLong("identificador");
		}
		leerNota();
	}

	private void leerNota() {
		NotasOpenHelper dbHelper = new NotasOpenHelper(this);

		Nota nota = null;

		SQLiteDatabase db = dbHelper.getReadableDatabase();

		Cursor cursor = db.query("Nota", new String[] { "_id", "Titulo",
				"Contenido" }, "_id= ?",
				new String[] { String.valueOf(idNota) }, null, null, null);

		while (cursor.moveToNext()) {
			nota = new Nota();

			nota.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			nota.setTitulo(cursor.getString(cursor.getColumnIndex("Titulo")));
			nota.setContenido(cursor.getString(cursor
					.getColumnIndex("Contenido")));

		}

		cursor.close();

		db.close();

		dbHelper.close();

		if (nota != null) {
			EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
			EditText txtContenido = (EditText) findViewById(R.id.txtContenido);
			txtTitulo.setText(nota.getTitulo());
			txtContenido.setText(nota.getContenido());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		getMenuInflater().inflate(R.menu.menu_nota_detalle, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.accion_atras:
			finish();
			return true;

		case R.id.accion_grabar:
			grabarNota();
			return true;
		case R.id.accion_eliminar:
			eliminarNota();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private void grabarNota() {

		EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
		EditText txtContenido = (EditText) findViewById(R.id.txtContenido);

		String titulo = txtTitulo.getText().toString();
		String contenido = txtContenido.getText().toString();

		NotasOpenHelper dbHelper = new NotasOpenHelper(this);

		if (idNota == 0) {

			ContentValues valores = new ContentValues();
			valores.put("Titulo", titulo);
			valores.put("Contenido", contenido);

			SQLiteDatabase db = dbHelper.getWritableDatabase();

			idNota = db.insert("Nota", null, valores);
			db.close();

		} else {
			ContentValues valores = new ContentValues();
			valores.put("Titulo", titulo);
			valores.put("Contenido", contenido);

			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.update("Nota", valores, "_id = ? ",
					new String[] { String.valueOf(idNota) });

			db.close();
		}

		dbHelper.close();

		Toast.makeText(this, "Registro guardado correctamente",
				Toast.LENGTH_SHORT).show();

	}

	private void eliminarNota() {

		NotasOpenHelper dbHelper = new NotasOpenHelper(this);

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		db.delete("Nota", "_id = ? ", new String[] { String.valueOf(idNota) });

		db.close();

		dbHelper.close();

		Toast.makeText(this, "Registro eliminado correctamente",
				Toast.LENGTH_SHORT).show();
		finish();
	}

}
