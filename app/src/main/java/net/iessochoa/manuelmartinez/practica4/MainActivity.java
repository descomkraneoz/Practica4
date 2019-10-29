package net.iessochoa.manuelmartinez.practica4;

/**
 * @autor: Gracias a Ruben Mas por su ayuda
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_OPTION_NUEVA_POBLACIONES = 0;
    public static final int REQUEST_OPTION_EDITAR_POBLACIONES = 1;
    public static String STATE_LISTA_POBLACIONES = "net.iessochoa.manuelmartinez.practica4.PoblacionActivity.lista_poblaciones";

    //listView
    ListView lvListaPoblaciones;
    //adaptador de tipo arrayList para el ViewModel
    private PoblacionesAdapter adaptador;

    //array de muestras para practicar solo en el main, se llena con el metodo creaDatosDeEjemplo()
    ArrayList<Poblacion> poblaciones = new ArrayList<Poblacion>();

    //Botones del menu
    Button btAcercade;
    Button btOrdenar;
    Button btAnyadir;


    /**
     * Metodo para crear el mensaje de alerta al pulsar sobre el boton de Acerca de del Menu de la app
     */

    public void MensajeAcercade() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        // titulo y mensaje
        dialogo.setTitle(getResources().getString(R.string.acercadeTitulo));
        dialogo.setMessage(getResources().getString(R.string.acercadeMensaje));

        // agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso ok
                        onRestart();
                    }
                });
        dialogo.show();
    }

    /**
     * Metodo del boton (+) del menu que llama a la PoblacionActivity
     */

    public void agregaPoblacion() {
        Intent intent = new Intent(MainActivity.this, PoblacionActivity.class);
        startActivityForResult(intent, REQUEST_OPTION_NUEVA_POBLACIONES);
    }

    /**
     * Metodo para crear unos datos de muestra en el array, es un ejemplo de previsualización en la listView
     */

    public void creaDatosDeEjemplo() {

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
        adaptador = new PoblacionesAdapter(this, poblaciones);
        lvListaPoblaciones.setAdapter(adaptador);



        //Rellenar datos de ejemplo
        if (savedInstanceState == null) {
            creaDatosDeEjemplo();
        }


        //Metodo que actua cuando se hace un click corto sobre un item/elemento del arrayList y edita la poblacion
        lvListaPoblaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Intent intentEnviaDatos = new Intent(MainActivity.this, PoblacionActivity.class);
                //obtener el objeto pulsado
                Poblacion poblacionElegida = poblaciones.get(position);
                //editar el objeto pulsado
                editarPoblacion(poblacionElegida);
                intentEnviaDatos.putExtra(PoblacionActivity.EXTRA_POBLACION_RECIBIDA_A_EDITAR, poblacionElegida);
                startActivityForResult(intentEnviaDatos, REQUEST_OPTION_EDITAR_POBLACIONES);
            }
        });

        //Metodo que actua cuando se hace un click largo sobre un item/elemento del arrayList y borra la poblacion o no.
        lvListaPoblaciones.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //obtener el objeto pulsado
                Poblacion poblacionElegida = poblaciones.get(i);
                //metodo para preguntar si se desea o no eliminar dicho objeto
                EliminarPoblacionClickLargo(poblacionElegida);
                return true;
            }
        });
    }

    /**
     * Metodo para preguntar si se elimina, o no, una población del arrayList, salta con un click largo en este ejemplo
     */
    private void EliminarPoblacionClickLargo(final Poblacion poblacionElegida) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle(getResources().getString(R.string.borrarTitulo));
        dialogo.setMessage(getResources().getString(R.string.borrarMensaje) + poblacionElegida.getLocalidad());

        // agregamos botón Ok y su evento
        dialogo.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Borramos en caso de ok, primero del Main y luego del adaptador
                        MainActivity.this.borrarPoblacion(poblacionElegida);
                        adaptador.delPoblacion(poblacionElegida);
                        adaptador.notifyDataSetChanged();
                        onRestart();
                    }
                });

        dialogo.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Qué hacemos en caso cancel
                        Toast.makeText(MainActivity.this,
                                getResources().getString(R.string.mensajePoblacionNoEliminada)
                                , Toast.LENGTH_LONG).show();
                    }
                });
        dialogo.show();
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

    /**
     * Metodos CRUD para actuar sobre el arrayList del ActivityMain
     */

    private void anyadirPoblacion(Poblacion p) {
        if (poblaciones.contains(p)) {
            poblaciones.remove(p);
        }
        poblaciones.add(p);
        adaptador.notifyDataSetChanged();
    }

    private void borrarPoblacion(Poblacion p) {
        poblaciones.remove(p);
        adaptador.notifyDataSetChanged();
    }

    private void editarPoblacion(final Poblacion p) {
        poblaciones.get(poblaciones.indexOf(p)).setComentarios(p.getComentarios());
        poblaciones.get(poblaciones.indexOf(p)).setValoracion(p.getValoracion());
        adaptador.notifyDataSetChanged();
    }

    /**
     * Metodo para rescatar los datos mandados al PoblacionActivity y mostrarlos en el MainActivity
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_OPTION_NUEVA_POBLACIONES:
                    Poblacion p = data.getParcelableExtra(PoblacionActivity.EXTRA_POBLACION_A_GUARDAR);
                    anyadirPoblacion(p);
                    break;
                case REQUEST_OPTION_EDITAR_POBLACIONES:
                    Poblacion pi = data.getParcelableExtra(PoblacionActivity.EXTRA_POBLACION_A_GUARDAR);
                    editarPoblacion(pi);
                    lvListaPoblaciones.getAdapter();
                    break;
            }
        }
    }

    /**
     * eventos onSaveInstaceState y onRestoreInstanceState que se ejecutan antes de destruir la actividad y que nos permite guardar
     * en un Bundle los datos necesarios para recuperarlos en la reconstrucción de la actividad.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LISTA_POBLACIONES, poblaciones);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {

            for (Parcelable p : savedInstanceState.getParcelableArrayList(STATE_LISTA_POBLACIONES)) {
                poblaciones.add((Poblacion) p);
            }

            adaptador.notifyDataSetChanged();
        } else {
            creaDatosDeEjemplo();
        }
    }
}
