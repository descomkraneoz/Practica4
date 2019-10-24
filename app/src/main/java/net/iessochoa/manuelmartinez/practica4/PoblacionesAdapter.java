package net.iessochoa.manuelmartinez.practica4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class PoblacionesAdapter extends ArrayAdapter<Poblacion> {
    //El array de objetos que voy a mostrar
    private ArrayList<Poblacion> poblacionesValoradas;

    //Constructor de clase
    public PoblacionesAdapter(Context context, int resource, ArrayList<Poblacion> objects) {
        super(context, 0, objects);
        poblacionesValoradas = objects;
    }

    /**
     * devuelve la lista actual de elementos
     */
    public ArrayList<Poblacion> getPoblacionesValoradas() {
        return poblacionesValoradas;
    }

    /**
     * Nos permite actualizar la lista e indicar al adaptador que reconstrulla la lista
     */
    public void setPoblacionesValoradas(ArrayList<Poblacion> poblacionesValoradas) {
        this.poblacionesValoradas = poblacionesValoradas;
        notifyDataSetChanged();//actualizamos el ListView
    }

    /**
     *  Nos permite asignar los datos de un elemento de la lista en el listview
     * @param position posicion del elemento a mostrar
     * @param convertView layaout de un elemento que se deja de mostrar en pantalla, es reciclable para ahorrar recursos
     * @param parent
     * @return
     */

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
                //System.out.println("Poblacion ya en la lista, editar: ");
                editPoblacion(poblacion);
            } else {
                poblacionesValoradas.add(poblacion);
                this.notifyDataSetChanged();
            }
        } else {
            //System.out.println("Error objeto vacio");
            return;
        }
    }

    public void delPoblacion(int index) {
        poblacionesValoradas.remove(index);
        this.notifyDataSetChanged();
    }


}
