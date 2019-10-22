package net.iessochoa.manuelmartinez.practica4;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btAcercade;
    Button btOrdenar;
    Button btAnyadir;

    private PoblacionesAdapter adaptadorLocalidadesValoradas;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAcercade=findViewById(R.id.btAcercade);
        btAnyadir=findViewById(R.id.btAnyadir);
        btOrdenar=findViewById(R.id.btOrdenar);

        //Agregar fragmento a la actividad

        ListaPoblaciones listaPoblaciones = (ListaPoblaciones) getSupportFragmentManager().findFragmentById(R.id.lvListaPoblaciones);
        if (listaPoblaciones == null) {
            //listaPoblaciones = ListaPoblaciones.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.lvListaPoblaciones, listaPoblaciones).commit();
        }

        //Crear y asignar un ArrayList al adaptador y asignarlo al listView

        adaptadorLocalidadesValoradas = new PoblacionesAdapter(this, R.layout.fragment_lista_poblaciones, listaPoblaciones);
        lsv_LocalidadesValoradas.setAdapter(adaptadorLocalidadesValoradas);


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

                break;
            case R.id.btOrdenar:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
