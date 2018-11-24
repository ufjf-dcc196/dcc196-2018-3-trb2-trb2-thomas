package br.ufjf.dcc196.dcc196_trb1.Database;

public class ScriptDLL {

    public static String getCreateTableParticipante(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Participante ( ");
        sql.append("        codigo INTEGER       PRIMARY KEY AUTOINCREMENT NOT NULL, " );
        sql.append("        nome   VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("        email  VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("        cpf    VARCHAR (250) DEFAULT ('') NOT NULL ) ");

        return sql.toString();
    }

    public static String getCreateTableEvento(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS Evento ( ");
        sql.append("        codigo      INTEGER       PRIMARY KEY AUTOINCREMENT NOT NULL, " );
        sql.append("        titulo      VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("        dia         DATE (250)    NOT NULL DEFAULT (''), ");
        sql.append("        hora        TIME (250)    NOT NULL DEFAULT (''), ");
        sql.append("        facilitador VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("        descricao   VARCHAR (250) NOT NULL DEFAULT ('') ) ");

        return sql.toString();
    }
}
