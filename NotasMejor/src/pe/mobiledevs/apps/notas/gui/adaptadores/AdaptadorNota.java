package pe.mobiledevs.apps.notas.gui.adaptadores;

import java.util.List;

import pe.mobiledevs.apps.notas.R;
import pe.mobiledevs.apps.notas.modelo.entidades.Nota;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorNota extends BaseAdapter {

	private LayoutInflater inflater = null;
	private List<Nota> notas = null;

	public AdaptadorNota(Context context, List<Nota> notas) {
		this.inflater = LayoutInflater.from(context);
		this.notas = notas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return notas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return notas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return notas.get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (view == null) {
			view = inflater.inflate(R.layout.lista_plantilla_simple, parent,
					false);
		}

		Nota nota = notas.get(position);

		if (nota != null) {

			TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
			txtTitulo.setText(nota.getTitulo());
		}

		return view;
	}
}
