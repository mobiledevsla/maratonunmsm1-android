package pe.mobiledevs.apps.notas.modelo.proveedores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotasOpenHelper extends SQLiteOpenHelper {

	private static final String NOMBRE_BASE_DATOS = "pe.mobiledevs.apps.notas.db";
	private static final int VERSION_BASE_DATOS = 1;

	public NotasOpenHelper(Context context) {
		super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE Nota( _id  INTEGER PRIMARY KEY, Titulo TEXT, Contenido TEXT)");

		ContentValues values1 = new ContentValues();
		values1.put("Titulo", "Nota 1");
		values1.put("Contenido", "Mi primera nota");

		db.insert("Nota", null, values1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
