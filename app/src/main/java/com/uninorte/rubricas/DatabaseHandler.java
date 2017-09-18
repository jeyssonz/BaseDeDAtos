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
    private static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "Rubric.db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Estudiantes ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR , codigo INTEGER, curso INTEGER);");
        db.execSQL("create table Cursos ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR);");
        db.execSQL("create table Rubricas ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR);");
        db.execSQL("create table Rubrica ( id INTEGER PRIMARY KEY AUTOINCREMENT,categoria VARCHAR, peso VARCHAR, elementos VARCHAR, l1 INTEGER, l2 INTEGER, l3 INTEGER, l4 INTEGER, rubricaid INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Estudiantes");
        db.execSQL("DROP TABLE IF EXISTS Cursos");
        db.execSQL("DROP TABLE IF EXISTS Rubricas");
        db.execSQL("DROP TABLE IF EXISTS Rubrica");
        onCreate(db);
    }

    public void insertCursos(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Cursos", null, contentValues);
        database.close();
    }
    public void insertR(String nombre) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        database.insert("Rubricas", null, contentValues);
        database.close();
    }
    public void insertEstudiante(String nombre, int codigo, int idcurso) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("codigo",codigo);
        contentValues.put("curso", idcurso);
        database.insert("Estudiantes", null, contentValues);
        database.close();
    }
    public void insertRubri(String categoria, String peso, String elementos, int l1, int l2, int l3, int l4 ,int rubri) {
        this.database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoria",categoria);
        contentValues.put("peso",peso);
        contentValues.put("elementos",elementos);
        contentValues.put("l1",l1);
        contentValues.put("l2",l2);
        contentValues.put("l3",l3);
        contentValues.put("l4",l4);
        contentValues.put("rubricaid",rubri);
        database.insert("Rubrica", null, contentValues);
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
    public ArrayList s(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table + " WHERE curso = "+ posicion, null);
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
    public ArrayList se(String table, int posicion) {
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT categoria,peso,elementos FROM " + table + " WHERE rubricaid = "+ posicion, null);
        ArrayList array = new ArrayList();
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                array.add(i, cursor.getString(1));
                array.add(i, cursor.getString(2));
                array.add(i, cursor.getString(0));//aaa
            }
        }
        cursor.close();
        database.close();
        return array;
    }

}