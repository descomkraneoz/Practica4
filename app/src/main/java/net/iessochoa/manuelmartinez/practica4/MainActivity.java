package net.iessochoa.manuelmartinez.practica4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_OPTION_NUEVA_POBLACIONES = 0;
    public static final int REQUEST_OPTION_EDITAR_POBLACIONES = 1;

    //listView
    ListView lvListaPoblaciones;
    //adaptador de tipo arrayList para el ViewModel
    private PoblacionesAdapter adaptador;
    //El viewModel definido para mantener los datos(la lista) que no queramos perder en la reconstrucción

    //array de muestras para practicar solo en el main, se llena con el metodo creaDatos()
    ArrayList<Poblacion> poblaciones = new ArrayList<Poblacion>();

    Button btAcercade;
    Button btOrdenar;
    Button btAnyadir;


    /**
     * Metodo para crear el mensaje al pulsar sobre el boton de Acerca de del Menu de la app
     */

    public void MensajeAcercade() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Acerca de ");// titulo y mensaje
        dialogo.setMessage("Práctica 4\n" +
                "Manuel Martínez Serrano\n" +
                "Licencia cc (Creative Commons)");


        // agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok ....
                        //onDestroy(); no funciona como es debido en este caso
                        onRestart();
                    }
                });

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
        Intent intent = new Intent(MainActivity.this, PoblacionActivity.class);
        startActivityForResult(intent, 0);
        //Toast.makeText(getApplicationContext(), getResources().getText(R.string.tmMensajeERROR), Toast.LENGTH_LONG).show();

    }

    /**
     * Metodo para crear unos datos de muestra en el array
     */

    public void creaDatos() {

        poblaciones.add(new Poblacion("Alicante", "Elche", 4.0f, "Lorem ipsum dolor sit amet," +
                " consectetur adipiscing elit. Integer sed finibus ipsum. " +
                "Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. " +
                "Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula "));
        poblaciones.add(new Poblacion("Alicante", "Alcoy", 2.0f, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Integer sed finibus ipsum. " +
                "Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. " +
                "Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula ullamcorper."));
        poblaciones.add(new Poblacion("Alicante", "Orihuela", 3.5f, "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Integer sed finibus ipsum. " +
                "Curabitur non fermentum urna. Aliquam risus nunc, dapibus vitae commodo at, sollicitudin eget diam. " +
                "Nunc consequat magna at fermentum maximus. Duis venenatis rutrum neque, mattis pulvinar purus vehicula ullamcorper."));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListaPoblaciones = findViewById(R.id.lvListaPoblaciones);
        btAcercade = findViewById(R.id.btAcercade);
        btAnyadir = findViewById(R.id.btAnyadir);
        btOrdenar = findViewById(R.id.btOrdenar);

        //Crear y asignar un ArrayList al adaptador y asignarlo al listView
        creaDatos();

        adaptador = new PoblacionesAdapter(this, poblaciones);
        lvListaPoblaciones.setAdapter(adaptador);


        lvListaPoblaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                //obtener el objeto pulsado
                adaptador.getItem(position);
                System.out.println(adaptador.getItem(position));
            }
        });
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
            case (R.id.btAnyadir):
                agregaPoblacion();
                break;
            case R.id.btOrdenar:
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.tmMensajeERROR), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_OPTION_NUEVA_POBLACIONES:
                    Poblacion p = data.getParcelableExtra(PoblacionActivity.EXTRA_POBLACION_A_GUARDAR);
                    poblaciones.add(p);
                    adaptador.notifyDataSetChanged();
                    break;
                case REQUEST_OPTION_EDITAR_POBLACIONES:
                    Poblacion pi = data.getParcelableExtra(PoblacionActivity.EXTRA_POBLACION_A_EDITAR);
                    poblaciones.add(pi);
                    adaptador.notifyDataSetChanged();
                    System.out.println("Entra");
                    break;

            }
        }
    }
}
