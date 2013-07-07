package pe.mobiledevs.apps.notas.modelo.proveedores;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class NotasOpenHelper extends SQLiteOpenHelper {

	private static final String NOMBRE_BASE_DATOS = "pe.mobiledevs.apps.notas.dbMejorada";
	private static final int VERSION_BASE_DATOS = 1;

	public NotasOpenHelper(Context context) {
		super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		StringBuilder sqlCrearTablaNotas = new StringBuilder();

		sqlCrearTablaNotas.append("CREATE TABLE " + TablaNota.NOMBRE_TABLA
				+ "(");
		sqlCrearTablaNotas.append(TablaNota.Columnas._ID
				+ " INTEGER PRIMARY KEY,");
		sqlCrearTablaNotas.append(TablaNota.Columnas.TITULO + " TEXT,");
		sqlCrearTablaNotas.append(TablaNota.Columnas.CONTENIDO + " TEXT");

		sqlCrearTablaNotas.append(")");

		db.execSQL(sqlCrearTablaNotas.toString());

		ContentValues values1 = new ContentValues();

		values1.put(TablaNota.Columnas.TITULO, "Nota 1");
		values1.put(TablaNota.Columnas.CONTENIDO, "Mi primera nota");
		db.insert(TablaNota.NOMBRE_TABLA, null, values1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public static final class TablaNota {
		public static final String NOMBRE_TABLA = "Nota";

		public static final class Columnas implements BaseColumns {
			public static final String TITULO = "Titulo";
			public static final String CONTENIDO = "Contenido";
		}

	}

}
