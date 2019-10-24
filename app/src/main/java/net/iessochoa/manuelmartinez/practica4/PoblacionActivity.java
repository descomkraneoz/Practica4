package net.iessochoa.manuelmartinez.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PoblacionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Constante clave-valor para recuperar los datos en la reconstrucción
    public static String STATE_LISTA_POBLACIONES = "net.iessochoa.manuelmartinez.practica4.PoblacionActivity.lista_poblaciones";


    //datos para la  lista
    ArrayList<Poblacion> al_datos;
    PoblacionesAdapter adaptadorLista;

    //Elementos de la actividad
    TextView tvProvincia;
    TextView tvLocalidad;
    Spinner spProvincia;
    Spinner spLocalidad;
    RatingBar rbEstrellas;
    TextView tvComentarios;
    EditText etComentarios;
    FloatingActionButton fabGuardar;

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
        fabGuardar = findViewById(R.id.fabGuardar);

        if (savedInstanceState == null) {
            //crear datos a mostrar
            //podemos poner un metodo que lo haga, por ejemplo: crearDatos();
        } else {
            //recuperamos los datos

            /*   //////// por aqui /////
            al_datos=savedInstanceState.getParcelableArrayList(STATE_LISTA_POBLACIONES);
            //https://github.com/jlopezdemerlo/EjemplosPractica4/blob/master/app/src/main/java/net/iessochoa/joseantoniolopez/ejemplospractica4/ListaConReconstruccionActivity.java

             */
        }

        /**
         * Metodo para informar del voto del RatingBar
         */

        rbEstrellas.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(PoblacionActivity.this, getResources().getText(R.string.tmMensajeRatinBar) + "" + v, Toast.LENGTH_LONG).show();
            }
        });

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poblacion p = new Poblacion();
                p.setProvincia(spProvincia.toString());
                p.setLocalidad(spLocalidad.toString());
                p.setValoracion(rbEstrellas.getRating());
                p.setComentarios(etComentarios.toString());

            }
        });

        /**
         * Con este metodo consigo poblar el primer spinner con los datos del xml, pero tambien puedo usar
         * el entries del propio spinner en el activity_poblacion.xml
         */

        ArrayAdapter<CharSequence> adapterProvincia = ArrayAdapter.createFromResource(this,
                R.array.provincias,
                android.R.layout.simple_spinner_item);
        adapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvincia.setAdapter(adapterProvincia);
        spProvincia.setOnItemSelectedListener(this);

    }

    /**
     * Sobreescribimos el método onItemSelected para poder asociar los dos spinner entre si
     */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //creamos el array que almacene las localidades
        TypedArray arrayLocalidades;
        //Dotamos al array del contenido
        arrayLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        //Obtenemos las localidades mediante la posición del elemento seleccionado en Provincias
        CharSequence[] localidades = arrayLocalidades.getTextArray(position);

        //Creamos el adaptador y lo dotamos con los recursos
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, localidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLocalidad.setAdapter(adapter);
        //Mensaje que informa sobre la localidad seleccionada
        String ele = (String) parent.getItemAtPosition(position);
        Toast.makeText(getApplicationContext(), getString(R.string.tmMensajeSELECCIONADO) + ele, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), getString(R.string.tmNOSeleccion), Toast.LENGTH_LONG).show();
    }


}