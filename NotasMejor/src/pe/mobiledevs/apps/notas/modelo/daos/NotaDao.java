package pe.mobiledevs.apps.notas.modelo.daos;

import java.util.ArrayList;
import java.util.List;

import pe.mobiledevs.apps.notas.modelo.entidades.Nota;
import pe.mobiledevs.apps.notas.modelo.proveedores.NotasOpenHelper.TablaNota;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotaDao {

	private SQLiteOpenHelper dbHelper = null;

	public NotaDao(SQLiteOpenHelper dbHelper) {
		this.dbHelper = dbHelper;
	}

	public List<Nota> obtenerTodasLasNotas() {
		List<Nota> notas = new ArrayList<Nota>();

		SQLiteDatabase db = this.dbHelper.getReadableDatabase();

		Cursor cursor = db.query(TablaNota.NOMBRE_TABLA, new String[] {
				TablaNota.Columnas._ID, TablaNota.Columnas.TITULO,
				TablaNota.Columnas.CONTENIDO }, null, null, null, null, null);

		while (cursor.moveToNext()) {
			Nota nota = new Nota();

			// Verificar si el id de la nota tiene un valor y si tiene asignarla
			// al objeto nota
			if (!cursor.isNull(cursor.getColumnIndex(TablaNota.Columnas._ID))) {
				nota.setId(cursor.getInt(cursor
						.getColumnIndex(TablaNota.Columnas._ID)));
			}

			// Verificar si el titulo de la nota tiene un valor y si tiene
			// asignarla
			// al objeto nota
			if (!cursor
					.isNull(cursor.getColumnIndex(TablaNota.Columnas.TITULO))) {
				nota.setTitulo(cursor.getString(cursor
						.getColumnIndex(TablaNota.Columnas.TITULO)));
			}

			// Verificar si el contenido de la nota tiene un valor y si tiene
			// asignarla
			// al objeto nota
			if (!cursor.isNull(cursor
					.getColumnIndex(TablaNota.Columnas.CONTENIDO))) {
				nota.setContenido(cursor.getString(cursor
						.getColumnIndex(TablaNota.Columnas.CONTENIDO)));
			}

			notas.add(nota);
		}

		if (!cursor.isClosed()) {
			cursor.close();
		}

		if (db.isOpen()) {
			db.close();
		}

		return notas;

	}

	public Nota obtenerNotaPorIdentificador(long id) {
		Nota nota = null;

		SQLiteDatabase db = this.dbHelper.getReadableDatabase();

		Cursor cursor = db.query(TablaNota.NOMBRE_TABLA, new String[] {
				TablaNota.Columnas._ID, TablaNota.Columnas.TITULO,
				TablaNota.Columnas.CONTENIDO },
				TablaNota.Columnas._ID + " = ?",
				new String[] { String.valueOf(id) }, null, null, null);

		while (cursor.moveToNext()) {
			nota = new Nota();

			// Verificar si el id de la nota tiene un valor y si tiene asignarla
			// al objeto nota
			if (!cursor.isNull(cursor.getColumnIndex(TablaNota.Columnas._ID))) {
				nota.setId(cursor.getInt(cursor
						.getColumnIndex(TablaNota.Columnas._ID)));
			}

			// Verificar si el titulo de la nota tiene un valor y si tiene
			// asignarla
			// al objeto nota
			if (!cursor
					.isNull(cursor.getColumnIndex(TablaNota.Columnas.TITULO))) {
				nota.setTitulo(cursor.getString(cursor
						.getColumnIndex(TablaNota.Columnas.TITULO)));
			}

			// Verificar si el contenido de la nota tiene un valor y si tiene
			// asignarla
			// al objeto nota
			if (!cursor.isNull(cursor
					.getColumnIndex(TablaNota.Columnas.CONTENIDO))) {
				nota.setContenido(cursor.getString(cursor
						.getColumnIndex(TablaNota.Columnas.CONTENIDO)));
			}

		}

		if (!cursor.isClosed()) {
			cursor.close();
		}

		if (db.isOpen()) {
			db.close();
		}

		return nota;

	}

	public long insertarNota(Nota nota) {

		long id = 0;

		ContentValues valores = new ContentValues();

		valores.put(TablaNota.Columnas.TITULO, nota.getTitulo());
		valores.put(TablaNota.Columnas.CONTENIDO, nota.getContenido());

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		id = db.insert(TablaNota.NOMBRE_TABLA, null, valores);
		db.close();

		return id;

	}

	public void actualizarNota(Nota nota) {

		ContentValues valores = new ContentValues();
		valores.put(TablaNota.Columnas.TITULO, nota.getTitulo());
		valores.put(TablaNota.Columnas.CONTENIDO, nota.getContenido());

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.update(TablaNota.NOMBRE_TABLA, valores, TablaNota.Columnas._ID
				+ " = ? ", new String[] { String.valueOf(nota.getId()) });

		db.close();
	}

	public void eliminarNota(long id) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		db.delete(TablaNota.NOMBRE_TABLA, TablaNota.Columnas._ID + " = ? ",
				new String[] { String.valueOf(id) });

		db.close();
	}
}
