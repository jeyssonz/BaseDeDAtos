package com.uninorte.rubricas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Andres Rolong on 13/09/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Rubric.db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Estudiantes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR , idcursos INTEGER);");
        db.execSQL("create table Cursos ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR);");
        db.execSQL("create table Pivot_Estudiantes_Cursos ( id_curso INTEGER, id_estudiante INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Estudiantes");
        db.execSQL("DROP TABLE IF EXISTS Cursos");
        db.execSQL("DROP TABLE IF EXISTS Pivot_Estudiantes_Cursos");
        onCreate(db);
    }

    public void insertCursos(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Cursos", null, contentValues);
        database.close();
    }
    public void insertEstudiante(String nombre, int id ) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("id", id);
        database.insert("Estudiantes", null, contentValues);
        database.close();
    }
    public ArrayList select(String table) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(1));
            }
        }
        cursor.close();
        database.close();
        return array;
    }

}