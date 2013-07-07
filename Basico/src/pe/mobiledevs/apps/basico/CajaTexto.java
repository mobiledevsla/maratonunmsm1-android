package pe.mobiledevs.apps.basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CajaTexto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_caja_texto);

		Button btnAccion = (Button) findViewById(R.id.btnAccion);
		btnAccion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText txtTexto = (EditText) findViewById(R.id.txtTexto);
				Toast.makeText(CajaTexto.this,
						"El texto es: " + txtTexto.getText().toString(),
						Toast.LENGTH_SHORT).show();

			}
		});

	}
}
