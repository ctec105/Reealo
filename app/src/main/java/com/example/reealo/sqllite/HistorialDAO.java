package com.example.reealo.sqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistorialDAO {

    private DbHelper _dbHelper;

    public HistorialDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void insertar(String busqueda) throws DAOException {
        Log.i("HistorialDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{busqueda, getDateTime()};
            db.execSQL("INSERT INTO historial(busqueda,fecha) VALUES(?,?)", args);
            Log.i("HistorialDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("HistorialDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void eliminar(int id) throws DAOException {
        Log.i("HistorialDAO", "eliminar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            String[] args = new String[]{String.valueOf(id)};
            db.execSQL("DELETE FROM historial WHERE id=?", args);
        } catch (Exception e) {
            throw new DAOException("HistorialDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Historial> buscar(String criterio) throws DAOException {
        Log.i("HistorialDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Historial> lista = new ArrayList<Historial>();
        try {
            Cursor c = db.rawQuery("select id, busqueda, fecha from historial where busqueda like '%" + criterio + "%'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    String busqueda = c.getString(c.getColumnIndex("busqueda"));
                    String fecha = c.getString(c.getColumnIndex("fecha"));

                    Historial modelo = new Historial();
                    modelo.setId(id);
                    modelo.setBusqueda(busqueda);
                    modelo.setFecha(fecha);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("HistorialDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }

    /*public GeneroMusical obtener() throws DAOException {
        Log.i("GeneroMusicalDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        GeneroMusical modelo = new GeneroMusical();
        try {
            Cursor c = db.rawQuery("select id, titulo, descripcion from genero", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    String titulo = c.getString(c.getColumnIndex("titulo"));
                    String descripcion = c.getString(c.getColumnIndex("descripcion"));

                    modelo.setId(id);
                    modelo.setTitulo(titulo);
                    modelo.setDescripcion(descripcion);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("GeneroMusicalDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }





    public void eliminarTodos() throws DAOException {
        Log.i("GeneroMusicalDAO", "eliminarTodos()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM genero");
        } catch (Exception e) {
            throw new DAOException("GeneroMusicalDAO: Error al eliminar todos: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }*/

}
