package br.usjt.desvmob.geodata.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.io.IOException;

import br.usjt.desvmob.geodata.R;
import br.usjt.desvmob.geodata.database.Database;
import br.usjt.desvmob.geodata.database.Network;
import br.usjt.desvmob.geodata.model.Pais;
/**
 * Tatiane Ayres 816118367
 */

public class MainActivity extends Activity {
    public static final String PAIS = "br.usjt.desvmob.geodata.view.MainActivity.ListaPaises";
    private Spinner spinContinente;
    private Context context;

    private class JSONPaises extends AsyncTask<String, Void, Pais[]> {
        @Override
        protected Pais[] doInBackground(String... strings) {

            Database db = new Database(context);
            Pais[] paises = db.selecionaPaises();
            if (paises == null || paises.length > 1) {
                try {
                    paises = Network.buscarPaises(strings[0]).toArray(new Pais[0]);

                    db.inserePaises(paises);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Log.d("Banco de dados","Países.");
            }
            return paises;
        }

        protected void onPostExecute(Pais[] paises) {
            Intent intent = new Intent(context, Listar.class);
            intent.putExtra(PAIS, paises);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        spinContinente = findViewById(R.id.continente);
    }

    public void listarPaises(View view) {
        String continente = spinContinente.getSelectedItem().toString();
        if (continente.equals("Todos")) {
            new JSONPaises().execute("https://restcountries.eu/rest/v2/all");
        } else {
            new JSONPaises().execute("https://restcountries.eu/rest/v2/region/" + continente);
        }
    }
}
