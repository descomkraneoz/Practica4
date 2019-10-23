package net.iessochoa.manuelmartinez.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PoblacionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tvProvincia;
    TextView tvLocalidad;
    Spinner spProvincia;
    Spinner spLocalidad;
    RatingBar rbEstrellas;
    TextView tvComentarios;
    EditText etComentarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion);
        tvProvincia = findViewById(R.id.tvProvincia);
        tvLocalidad = findViewById(R.id.tvLocalidad);
        spProvincia = findViewById(R.id.spProvincia);
        spLocalidad = findViewById(R.id.spLocalidad);
        rbEstrellas = findViewById(R.id.rbEstrellas);
        tvComentarios = findViewById(R.id.tvComentarios);
        etComentarios = findViewById(R.id.etComentarios);

        /**
         * Con este metodo consigo poblar el primer spinner con los datos del xml, pero no me hace falta si uso
         * el entries del propio spinner en el activity_poblacion.xml
         */

        ArrayAdapter<CharSequence> adapterProvincia = ArrayAdapter.createFromResource(this,
                R.array.provincias,
                android.R.layout.simple_spinner_item);
        adapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvincia.setAdapter(adapterProvincia);
        spProvincia.setOnItemSelectedListener(this);



        /*spProvincia.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        String ele=(String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(),getString(R.string.tmMensajeSELECCIONADO)+ ele,Toast.LENGTH_LONG).show();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(getApplicationContext(),getString(R.string.tmNOSeleccion),Toast.LENGTH_LONG).show();
                    }
                });*/

        /*ArrayAdapter<CharSequence> adapterLocalidad=ArrayAdapter.createFromResource(this,
                R.array.array_provincia_a_localidades,
                android.R.layout.simple_spinner_item);
        adapterLocalidad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spLocalidad.setAdapter(adapterLocalidad);
        spLocalidad.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        String ele=(String) parent.getItemAtPosition(position);
                        Toast.makeText(getApplicationContext(),getString(R.string.tmMensajeSELECCIONADO)+ ele,Toast.LENGTH_LONG).show();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(getApplicationContext(),getString(R.string.tmNOSeleccion),Toast.LENGTH_LONG).show();
                    }
                });*/

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int[] localidades = {R.array.localidades_alicante, R.array.localidades_alava, R.array.localidades_albacete, R.array.localidades_almeria,
                R.array.localidades_asturias, R.array.localidades_avila, R.array.localidades_badajoz, R.array.localidades_barcelona,
                R.array.localidades_burgos, R.array.localidades_caceres, R.array.localidades_cadiz, R.array.localidades_cantabria,
                R.array.localidades_castellon, R.array.localidades_ceuta, R.array.localidades_ciudadReal, R.array.localidades_cordoba,
                R.array.localidades_cuenca, R.array.localidades_gerona, R.array.localidades_granada, R.array.localidades_guadalajara,
                R.array.localidades_guipuzcoa, R.array.localidades_huelva, R.array.localidades_huesca, R.array.localidades_islasBaleares,
                R.array.localidades_jaen, R.array.localidades_laCoruna, R.array.localidades_laRioja, R.array.localidades_lasPalmas,
                R.array.localidades_leon, R.array.localidades_lerida, R.array.localidades_lugo, R.array.localidades_madrid,
                R.array.localidades_orense, R.array.localidades_palencia, R.array.localidades_pontevedra, R.array.localidades_salamanca,
                R.array.localidades_santaCruzDeTenerife, R.array.localidades_segovia, R.array.localidades_sevilla, R.array.localidades_soria,
                R.array.localidades_tarragona, R.array.localidades_teruel, R.array.localidades_toledo, R.array.localidades_valencia,
                R.array.localidades_valladolid, R.array.localidades_vizcaya, R.array.localidades_zamora, R.array.localidades_zaragoza};

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
