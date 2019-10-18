package net.iessochoa.manuelmartinez.practica4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btAcercade;
    Button btOrdenar;
    Button btAnyadir;

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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
