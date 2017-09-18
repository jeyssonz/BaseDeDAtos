package com.uninorte.rubricas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Formulario2 extends AppCompatActivity {

    private DatabaseHandler base = new DatabaseHandler(this);
    public static ArrayList listaR = new ArrayList();

    Spinner s;
    EditText categoria , peso, elementos, l1,l2,l3,l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        s = (Spinner) findViewById(R.id.cursos);
        listaR = base.select("Rubricas");
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listaR);
        s.setAdapter(itemsAdapter);
    }


    public void guardarest(View v){
        categoria = (EditText) findViewById(R.id.nombre);
        peso = (EditText) findViewById(R.id.codigo);
        elementos = (EditText) findViewById(R.id.e1);
        l1 = (EditText) findViewById(R.id.e2);
        l2 = (EditText) findViewById(R.id.e3);
        l3 = (EditText) findViewById(R.id.e4);
        l4 = (EditText) findViewById(R.id.e5);
        int j = s.getSelectedItemPosition() +1;
        String categor = categoria.getText().toString();
        String pes = peso.getText().toString();
        String elemento = elementos.getText().toString();
        int n1 = Integer.parseInt(l1.getText().toString());
        int n2 = Integer.parseInt(l2.getText().toString());
        int n3 = Integer.parseInt(l3.getText().toString());
        int n4 = Integer.parseInt(l4.getText().toString());
        base.insertRubri(categor,pes,elemento,n1,n2,n3,n4,j);

    }

}
