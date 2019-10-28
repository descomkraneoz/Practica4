package net.iessochoa.manuelmartinez.practica4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class PoblacionesAdapter extends BaseAdapter {
    protected Activity activity;
    //El array de objetos que voy a mostrar
    protected ArrayList<Poblacion> itemsPoblaciones;

    //Constructor de clase
    public PoblacionesAdapter(Activity activity, ArrayList<Poblacion> items) {
        this.activity = activity;
        this.itemsPoblaciones = items;
    }


    @Override
    public int getCount() {
        return itemsPoblaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsPoblaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * CRUD del metodo aunque no los voy a utilizar ya que hice los propios en el MainActivity
     */

    public void delPoblacion(Poblacion p) {
        itemsPoblaciones.remove(p);
        this.notifyDataSetChanged();
    }

    public void addPoblacion(ArrayList<Poblacion> category) {
        for (int i = 0; i < category.size(); i++) {
            itemsPoblaciones.add(category.get(i));
        }
        this.notifyDataSetChanged();
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
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_poblacion, null);
        }

        // Poblacion actual.
        Poblacion p = itemsPoblaciones.get(position);

        TextView tvLocalidad = (TextView) v.findViewById(R.id.tvLocalidad);
        tvLocalidad.setText(p.getLocalidad());

        TextView tvProvincia = (TextView) v.findViewById(R.id.tvProvincia);
        tvProvincia.setText(p.getProvincia());

        TextView tvComentarios = (TextView) v.findViewById(R.id.tvComentarios);
        tvComentarios.setText(p.getComentarios());

        RatingBar rbEstrellas = (RatingBar) v.findViewById(R.id.ratingBar);
        rbEstrellas.setRating(p.getValoracion());

        return v;
    }

}
