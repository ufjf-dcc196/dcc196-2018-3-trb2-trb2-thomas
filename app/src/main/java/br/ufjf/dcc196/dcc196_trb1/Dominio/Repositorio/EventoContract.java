package br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class EventoContract {

public final class Evento implements BaseColumns
{
    public static final String TABLE_NAME = "Evento";
    public static final String COLUMN_NAME_TITULO = "titulo";
    public static final String COLUMN_NAME_DIA = "dia";
    public static final String COLUMN_NAME_HORA = "hora";
    public static final String COLUMN_NAME_FACILITADOR = "facilitador";
    public static final String COLUMN_NAME_DESCRICAO = "descricao";
    public static final String CREATE_EVENTO = "CREATE TABLE " + Evento.TABLE_NAME + " ("
            + Evento._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Evento.COLUMN_NAME_TITULO + " TEXT, "
            + Evento.COLUMN_NAME_DIA + " DATE, "
            + Evento.COLUMN_NAME_HORA + " TEXT, "
            + Evento.COLUMN_NAME_FACILITADOR + " TEXT, "
            + Evento.COLUMN_NAME_DESCRICAO + " TEXT "
            +")";
    public static final String DROP_EVENTO = "DROP TABLE IF EXISTS " + Evento.TABLE_NAME;
}

    public static void inserir(SQLiteDatabase db, String titulo, String dia,
                                  String HORA, String facilitador, String descricao) {
        db.insert(EventoContract.Evento.TABLE_NAME,null,
                createContentValue(titulo, dia, HORA, facilitador, descricao));
    }

    public static void update(SQLiteDatabase db, int id, String titulo, String dia,
                                    String hora, String facilitador, String descricao) {
        db.update(Evento.TABLE_NAME,createContentValue(titulo, dia, hora, facilitador, descricao),
                Evento._ID + " = ?", new String[] {Integer.toString(id)});
    }

    private static ContentValues createContentValue(String titulo, String dia, String hora, String facilitador, String descricao)
    {
        ContentValues cv = new ContentValues();
        cv.put(EventoContract.Evento.COLUMN_NAME_TITULO, titulo);
        cv.put(EventoContract.Evento.COLUMN_NAME_DIA, dia);
        cv.put(EventoContract.Evento.COLUMN_NAME_HORA, hora);
        cv.put(EventoContract.Evento.COLUMN_NAME_FACILITADOR, facilitador);
        cv.put(EventoContract.Evento.COLUMN_NAME_DESCRICAO, descricao);
        return cv;
    }

    public static Cursor getEventoCursor(SQLiteDatabase db, String selection, String[] selectionArgs)
    {
        return db.query(EventoContract.Evento.TABLE_NAME, new String[] {EventoContract.Evento._ID, EventoContract.Evento.COLUMN_NAME_TITULO, EventoContract.Evento.COLUMN_NAME_HORA,
                EventoContract.Evento.COLUMN_NAME_DESCRICAO, EventoContract.Evento.COLUMN_NAME_FACILITADOR, EventoContract.Evento.COLUMN_NAME_DIA }, selection, selectionArgs,null,null,null,null);
    }
}
