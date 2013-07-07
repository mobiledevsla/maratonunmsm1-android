package pe.mobiledevs.apps.basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Botones extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.actividad_botones);

		Button btnConAccion = (Button) findViewById(R.id.btnConAccion);

		btnConAccion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Botones.this, "Gracias por darme click",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
