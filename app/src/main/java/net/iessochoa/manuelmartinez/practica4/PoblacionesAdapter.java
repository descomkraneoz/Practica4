package net.iessochoa.manuelmartinez.practica4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PoblacionesAdapter extends ArrayAdapter<Poblacion> {
    //El array de objetos que voy a mostrar
    private ArrayList<Poblacion> poblacionesValoradas;

    public PoblacionesAdapter(Context context, int resource, ArrayList<Poblacion> objects) {
        super(context, 0, objects);
        poblacionesValoradas = objects;
    }

    /**
     * devuelve la lista actual de elementos
     *
     * @return
     */
    public ArrayList<Poblacion> getPoblacionesValoradas() {
        return poblacionesValoradas;
    }

    public void setPoblacionesValoradas(ArrayList<Poblacion> lista) {
        this.poblacionesValoradas = lista;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.item_poblacion,
                    parent,
                    false);
        }

        // Referencias UI.

        TextView tvLocalidad = (TextView) convertView.findViewById(R.id.tvLocalidad);
        TextView tvProvincia = (TextView) convertView.findViewById(R.id.tvProvincia);
        TextView tvComentarios = (TextView) convertView.findViewById(R.id.tvComentarios);
        RatingBar rbEstrellas = (RatingBar) convertView.findViewById(R.id.ratingBar);

        // Poblacion actual.
        Poblacion poblacion = getItem(position);

        // Setup.

        tvLocalidad.setText(poblacion.getLocalidad());
        tvProvincia.setText(poblacion.getProvincia());
        tvComentarios.setText(poblacion.getComentarios());
        rbEstrellas.setRating(poblacion.getValoracion());

        return convertView;
    }

    public void editPoblacion(Poblacion poblacion) {
        poblacion.setLocalidad("");
        poblacion.setProvincia("");
        poblacion.setComentarios("");
        poblacion.setValoracion(0.0f);
    }

    public void addPoblacion(Poblacion poblacion) {
        if (poblacion != null) {
            if (poblacionesValoradas.contains(poblacion)) {
                System.out.println("Poblacion ya en la lista, editar: ");
                editPoblacion(poblacion);
            } else {
                poblacionesValoradas.add(poblacion);
                this.notifyDataSetChanged();
            }
        } else {
            System.out.println("Error objeto vacio");
        }
    }

    public void delPoblacion(int index) {
        poblacionesValoradas.remove(index);
        this.notifyDataSetChanged();
    }


}
