package br.ufjf.dcc196.dcc196_trb1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DadosOpenHelper extends SQLiteOpenHelper{
    public DadosOpenHelper(Context context) {
        super(context, "DADOSPARTICIPANTE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ScriptDLL.getCreateTableParticipante());
        sqLiteDatabase.execSQL(ScriptDLL.getCreateTableEvento());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
