package pe.mobiledevs.apps.notas.gui;

import pe.mobiledevs.apps.notas.R;
import pe.mobiledevs.apps.notas.modelo.daos.NotaDao;
import pe.mobiledevs.apps.notas.modelo.entidades.Nota;
import pe.mobiledevs.apps.notas.modelo.proveedores.NotasOpenHelper;
import android.app.Activity;
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
		NotaDao notaDao = new NotaDao(dbHelper);
		Nota nota = notaDao.obtenerNotaPorIdentificador(idNota);
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

		Nota nota = new Nota();
		nota.setId(idNota);
		nota.setTitulo(titulo);
		nota.setContenido(contenido);

		NotasOpenHelper dbHelper = new NotasOpenHelper(this);
		NotaDao notaDao = new NotaDao(dbHelper);

		if (idNota == 0) {
			idNota = notaDao.insertarNota(nota);
		} else {
			notaDao.actualizarNota(nota);
		}

		dbHelper.close();

		Toast.makeText(this, "Registro guardado correctamente",
				Toast.LENGTH_SHORT).show();

	}

	private void eliminarNota() {

		NotasOpenHelper dbHelper = new NotasOpenHelper(this);
		NotaDao notaDao = new NotaDao(dbHelper);

		notaDao.eliminarNota(idNota);

		dbHelper.close();

		Toast.makeText(this, "Registro eliminado correctamente",
				Toast.LENGTH_SHORT).show();
		finish();
	}

}
