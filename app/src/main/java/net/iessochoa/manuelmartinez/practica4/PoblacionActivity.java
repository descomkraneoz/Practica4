package net.iessochoa.manuelmartinez.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class PoblacionActivity extends AppCompatActivity {
    TextView tvProvincia;
    TextView tvLocalidad;
    Spinner spProvincia;
    Spinner spLocalidad;
    RatingBar rbEstrellas;
    TextView tvComentarios;
    EditText etComentarios;

    ArrayAdapter<CharSequence> adapterProvincia;
    ArrayAdapter<CharSequence> adapterLocalidad;

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

        ArrayAdapter.createFromResource(this, R.array.array_provincia_a_localidades, android.R.layout.simple_spinner_item);
        adapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvincia.setAdapter(adapterProvincia);
        spProvincia.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        etComentarios.setText("Seleccionado: " +
                                parent.getItemAtPosition(position));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        etComentarios.setText("");
                    }
                });
    }
}
