package pe.mobiledevs.apps.basico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Radio extends Activity {

	private Button btnMostrar = null;
	private RadioGroup grupo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad_radio);
		establecerControles();
	}

	private void establecerControles() {
		grupo = (RadioGroup) findViewById(R.id.grupo);
		btnMostrar = (Button) findViewById(R.id.btnMostrar);
		
		btnMostrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int idSeleccionado = grupo.getCheckedRadioButtonId();
				RadioButton radio = (RadioButton) findViewById(idSeleccionado);
				Toast.makeText(Radio.this, radio.getText().toString(),
						Toast.LENGTH_SHORT).show();

			}
		});

	}

}
