package net.iessochoa.manuelmartinez.practica4;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvListaPoblaciones;
    ArrayList<Poblacion> poblaciones;
    private PoblacionesAdapter adaptadorLocalidadesValoradas;

    Button btAcercade;
    Button btOrdenar;
    Button btAnyadir;




    /**
     * Metodo para crear el mensaje al pulsar sobre el boton de Acerca de del Menu de la app
     */

    public void MensajeAcercade(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Acerca de ");// titulo y mensaje
        dialogo.setMessage("Práctica 4\n"+
                "Manuel Martínez Serrano\n"+
                "Licencia cc (Creative Commons)");


        // agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.yes ,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok ....
                        onDestroy();
                    } });

        //Esta parte del código la comento porque no la voy a utilizar en este caso, sirve para un boton de cancelar
        /*dialogo.setNegativeButton(android.R.string.no ,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso cancel ......

                    } });*/

        dialogo.show();
    }

    /**
     * Metodo boton añadir
     */

    public void agregaPoblacion() {
        btAnyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Poblacion p = new Poblacion();
                p.setLocalidad("");
                p.setProvincia("");
                p.setComentarios("");
                p.setValoracion(0.0f);
                adaptadorLocalidadesValoradas.addPoblacion(p);
            }
        });
    }

    /**
     * Metodo para crear unos datos de muestra en el array
     */

    public void creaDatos() {
        poblaciones = new ArrayList<Poblacion>();
        poblaciones.add(new Poblacion("Alicante", "Elche", 4.0f, "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Integer sed finibus ipsum. " +
                "Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. " +
                "Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula "));
        poblaciones.add(new Poblacion("Alicante", "Alcoy", 2.0f, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Integer sed finibus ipsum. " +
                "Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. " +
                "Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula ullamcorper."));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListaPoblaciones = findViewById(R.id.lvListaPoblaciones);
        btAcercade=findViewById(R.id.btAcercade);
        btAnyadir=findViewById(R.id.btAnyadir);
        btOrdenar=findViewById(R.id.btOrdenar);

        //Crear y asignar un ArrayList al adaptador y asignarlo al listView
        creaDatos();

        adaptadorLocalidadesValoradas = new PoblacionesAdapter(this, R.layout.item_poblacion, poblaciones);
        lvListaPoblaciones.setAdapter(adaptadorLocalidadesValoradas);

        adaptadorLocalidadesValoradas.notifyDataSetChanged();


    }

    /**
     * @param menu, para poder mostrar el menu hace falta inflarlo antes.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Llamada a los botones o elementos del menu de la app
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btAcercade:
                MensajeAcercade();
                break;
            case R.id.btAnyadir:
                agregaPoblacion();
                break;
            case R.id.btOrdenar:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
