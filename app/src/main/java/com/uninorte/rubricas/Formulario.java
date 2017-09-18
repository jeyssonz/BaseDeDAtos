package com.uninorte.rubricas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Formulario extends AppCompatActivity {
    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listaC = new ArrayList();

    Spinner s;
    EditText nombre , codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        s = (Spinner) findViewById(R.id.cursos);
       listaC = base.select("Cursos");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaC);
        s.setAdapter(itemsAdapter);
    }


    public void guardarest(View v){
        nombre = (EditText) findViewById(R.id.nombre);
        codigo = (EditText) findViewById(R.id.codigo);
        int j = s.getSelectedItemPosition() +1;
        String estudiante = nombre.getText().toString();
        int cod = Integer.parseInt(codigo.getText().toString());
        base.insertEstudiante(estudiante, cod,j);

    }

}
