package net.iessochoa.manuelmartinez.practica4;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PoblacionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Constante clave-valor para recuperar los datos en la reconstrucción
    public static final String EXTRA_POBLACION_A_GUARDAR = "net.iessochoa.manuelmartinez.practica4.PoblacionActivity.poblacion_guardar";
    public static final String EXTRA_POBLACION_RECIBIDA_A_EDITAR = "net.iessochoa.manuelmartinez.practica4.PoblacionActivity.poblacion_editar";


    //datos para la  lista
    ArrayList<Poblacion> arrayListPoblacionActivity;
    PoblacionesAdapter adaptadorLista;
    ListView lvPoblacionActivity;

    //Elementos de la actividad
    TextView tvProvincia;
    TextView tvLocalidad;
    Spinner spProvincia;
    Spinner spLocalidad;
    RatingBar rbEstrellas;
    TextView tvComentarios;
    EditText etComentarios;
    FloatingActionButton fabGuardar;

    private void crearDatos() {
        arrayListPoblacionActivity = new ArrayList<Poblacion>();
        String Provincia = "Provincia";
        String Localidad = "Localidad";
        Float Valoracion = 2.0f;
        String Comentarios = "Donec ut lorem est. Suspendisse vel porttitor turpis. Aenean gravida elit nec sodales hendrerit. Vivamus non tellus eu sapien malesuada imperdiet. Sed eget diam vitae sem mattis scelerisque. Morbi sed elementum urna. Praesent egestas, nulla sit amet porttitor eleifend";
        for (int i = 0; i <= 10; i++) {
            arrayListPoblacionActivity.add(new Poblacion(Provincia + i, Localidad + i, Valoracion + i, Comentarios + i));
        }
    }

    private void rellenarDatosPoblacionEnviada() {
        Poblacion p = getIntent().getParcelableExtra(EXTRA_POBLACION_RECIBIDA_A_EDITAR);
        PoblacionActivity.this.setTitle(getResources().getString(R.string.editandoPoblacion) + p.getLocalidad());
        for (int i = 0; i < spProvincia.getCount(); i++) {
            if (spProvincia.getItemAtPosition(i).equals(p.getProvincia())) {
                spProvincia.setSelection(i);
                break;
            }
        }
        rbEstrellas.setRating(p.getValoracion());
        etComentarios.setText(p.getComentarios());
    }

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
        //this.setTitle(getResources().getText(R.string.nuevaPoblacion));

        if (getIntent().getParcelableExtra(EXTRA_POBLACION_RECIBIDA_A_EDITAR) != null) {
            rellenarDatosPoblacionEnviada();
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

        /**
         * Metodo para guardar y mandar la nueva poblacion al MainActivity
         */

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Poblacion p = new Poblacion(spProvincia.getSelectedItem().toString(),
                        spLocalidad.getSelectedItem().toString(),
                        rbEstrellas.getRating(), etComentarios.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(EXTRA_POBLACION_A_GUARDAR, p);
                if (getIntent().getParcelableExtra(EXTRA_POBLACION_RECIBIDA_A_EDITAR) != null) {
                    intent.putExtra(EXTRA_POBLACION_RECIBIDA_A_EDITAR, getIntent().getParcelableExtra(EXTRA_POBLACION_RECIBIDA_A_EDITAR));
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        /**
         * Con esto consigo poblar el primer spinner con los datos del xml, pero tambien puedo usar
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