package com.uninorte.rubricas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Estudiantes extends AppCompatActivity {
    public ListView list;
    public static ArrayList listaEstudiante = new ArrayList();
    private DatabaseHandler base = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialogo();
            }
        });
        Intent i = getIntent();
        int po=i.getIntExtra("Pos",0);
        listaEstudiante = base.s("Estudiantes",po);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaEstudiante);
        list = (ListView) findViewById(R.id.listaestudiante);
        list.setAdapter(itemsAdapter);
    }

    public void dialogo(){
        Intent i = new Intent(this, Formulario.class);
        startActivity(i);

    }
}
