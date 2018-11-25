package br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Evento;

public class EventoRepositorio {

    private SQLiteDatabase connection;

    public EventoRepositorio(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void inserir(Evento evento){

        ContentValues contentValues = new ContentValues();
        contentValues.put("TITULO", evento.getTitulo());
        contentValues.put("DIA", evento.getDia());
        contentValues.put("HORA", evento.getHora());
        contentValues.put("FACILITADOR", evento.getFacilitador());
        contentValues.put("DESCRICAO", evento.getDescricao());

        connection.insertOrThrow("Evento", null, contentValues);
    }

    public void excluir(int codigo){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        connection.delete("Evento", "codigo = ?", parametros);

    }

    public void alterar(Evento evento){

        ContentValues contentValues = new ContentValues();
        contentValues.put("TITULO", evento.getTitulo());
        contentValues.put("DIA", evento.getDia());
        contentValues.put("HORA", evento.getHora());
        contentValues.put("FACILITADOR", evento.getFacilitador());
        contentValues.put("DESCRICAO", evento.getDescricao());

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(evento.getCodigo());

        connection.update("Evento", contentValues, "codigo = ?", parametros);

    }

    public List<Evento> buscarTodos(){

        List<Evento> eventos = new ArrayList<Evento>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, TITULO, DIA, HORA, FACILITADOR, DESCRICAO ");
        sql.append("    FROM EVENTO ");

        Cursor resultado = connection.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0) {
            resultado.moveToFirst();


            do {

                Evento evento= new Evento();

                evento.setCodigo(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                evento.setTitulo(resultado.getString(resultado.getColumnIndexOrThrow("TITULO")));
                evento.setDia(resultado.getString(resultado.getColumnIndexOrThrow("DIA")));
                evento.setHora(resultado.getString(resultado.getColumnIndexOrThrow("HORA")));
                evento.setFacilitador(resultado.getString(resultado.getColumnIndexOrThrow("FACILITADOR")));
                evento.setDescricao(resultado.getString(resultado.getColumnIndexOrThrow("DESCRICAO")));

                eventos.add(evento);

            } while (resultado.moveToNext());
        }

        return eventos;
    }

    public Evento buscarEvento(int codigo){

        Evento evento = new Evento();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, TITULO, DIA, HORA, FACILITADOR, DESCRICAO ");
        sql.append("    FROM EVENTO ");
        sql.append(" WHERE CODIGO = ? ");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = connection.rawQuery(sql.toString(), parametros);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();

            evento.setCodigo(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
            evento.setTitulo(resultado.getString(resultado.getColumnIndexOrThrow("TITULO")));
            evento.setDia(resultado.getString(resultado.getColumnIndexOrThrow("DIA")));
            evento.setHora(resultado.getString(resultado.getColumnIndexOrThrow("HORA")));
            evento.setFacilitador(resultado.getString(resultado.getColumnIndexOrThrow("FACILITADOR")));
            evento.setDescricao(resultado.getString(resultado.getColumnIndexOrThrow("DESCRICAO")));

            return evento;
        }

        return null;
    }
}
