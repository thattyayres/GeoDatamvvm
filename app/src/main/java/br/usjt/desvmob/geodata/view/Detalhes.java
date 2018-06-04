package br.usjt.desvmob.geodata.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.usjt.desvmob.geodata.R;
import br.usjt.desvmob.geodata.model.Pais;

/**
 * Tatiane Ayres 816118367
 */

public class Detalhes extends Activity {

    /**
     * Detalhes sobre o país selecionado pelo usuário.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pais);
        TextView txtPais = (TextView)findViewById(R.id.txtPais);
        Intent intent = getIntent();
        Pais pais = (Pais)intent.getSerializableExtra(Listar.PAIS);
        txtPais.setText(pais.toString());
    }
}
