package br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;

public class ParticipanteRepositorio {

    private SQLiteDatabase connection;

    public ParticipanteRepositorio(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void inserir(Participante participante){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", participante.getNome());
        contentValues.put("EMAIL", participante.getEmail());
        contentValues.put("CPF", participante.getCpf());

        connection.insertOrThrow("Participante", null, contentValues);
    }

    public void excluir(int codigo){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        connection.delete("Participante", "codigo = ?", parametros);

    }

    public void alterar(Participante participante){

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", participante.getNome());
        contentValues.put("EMAIL", participante.getEmail());
        contentValues.put("CPF", participante.getCpf());

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(participante.getCodigo());

        connection.update("Participante", contentValues, "codigo = ?", parametros);

    }

    public List<Participante> buscarTodos(){

        List<Participante> participantes = new ArrayList<Participante>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, EMAIL, CPF ");
        sql.append("    FROM PARTICIPANTE ");

        Cursor resultado = connection.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0) {
            resultado.moveToFirst();


            do {

                Participante participante = new Participante();

                participante.setCodigo(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
                participante.setNome(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
                participante.setEmail(resultado.getString(resultado.getColumnIndexOrThrow("EMAIL")));
                participante.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("CPF")));

                participantes.add(participante);

            } while (resultado.moveToNext());
        }

        return participantes;
    }

    public Participante buscarParticipante(int codigo){

        Participante participante = new Participante();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT CODIGO, NOME, EMAIL, CPF ");
        sql.append("    FROM PARTICIPANTE ");
        sql.append(" WHERE CODIGO = ? ");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = connection.rawQuery(sql.toString(), parametros);

        if(resultado.getCount() > 0) {

            resultado.moveToFirst();

            participante.setCodigo(resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO")));
            participante.setNome(resultado.getString(resultado.getColumnIndexOrThrow("NOME")));
            participante.setEmail(resultado.getString(resultado.getColumnIndexOrThrow("EMAIL")));
            participante.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("CPF")));

            return participante;
        }

        return null;
    }
}
