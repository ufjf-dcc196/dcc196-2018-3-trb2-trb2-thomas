package br.ufjf.dcc196.dcc196_trb1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.EventoContract;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.ParticipanteContract;

public class DadosOpenHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dados.db";


    public DadosOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ParticipanteContract.Participante.CREATE_PARTICIPANTE);
        sqLiteDatabase.execSQL(EventoContract.Evento.CREATE_EVENTO);

        listaParticipantesBD(sqLiteDatabase);
        listaEventosBD(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void listaParticipantesBD(SQLiteDatabase sqLiteDatabase) {

        ParticipanteContract.insere(sqLiteDatabase,"Aluno1", "aluno1@hotmail.com", "11111111111");
        ParticipanteContract.insere(sqLiteDatabase,"Aluno2", "aluno2@hotmail.com", "22222222222");
        ParticipanteContract.insere(sqLiteDatabase,"Aluno3", "aluno3@hotmail.com", "33333333333");

    }

    public void listaEventosBD(SQLiteDatabase sqLiteDatabase) {
        EventoContract.inserir(sqLiteDatabase,"Evento1", "Dia 1", "01:00", "Professor1", "Workshop1");
        EventoContract.inserir(sqLiteDatabase,"Evento2", "Dia 2", "02:00", "Professor2", "Workshop2");
        EventoContract.inserir(sqLiteDatabase,"Evento3", "Dia 3", "03:00", "Professor3", "Workshop3");

    }
}
