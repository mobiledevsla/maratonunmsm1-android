package pe.mobiledevs.apps.basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuSimple extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		// TODO Auto-generated method stub

		getMenuInflater().inflate(R.menu.menu_simple, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.menu1:
			Toast.makeText(this, "Se dio click en el menú 1",
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menu2:
			Toast.makeText(this, "Se dio click en el menú 2",
					Toast.LENGTH_SHORT).show();

			return true;
		case R.id.menu3:
			Toast.makeText(this, "Se dio click en el menú 3",
					Toast.LENGTH_SHORT).show();

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
