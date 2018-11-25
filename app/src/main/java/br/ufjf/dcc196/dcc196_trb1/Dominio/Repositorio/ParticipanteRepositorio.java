package br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

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

        return null;
    }

    public Participante buscarParticipante(){

        return null;
    }
}
