package br.ufjf.dcc196.dcc196_trb1.Database;

public class ScriptDLL {

    public static String getCreateTableParticipante(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS PARTICIPANTE ( ");
        sql.append("    CODIGO   INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    NOME     VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    EMAIL    VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    CPF VARCHAR(20)  NOT NULL DEFAULT('') ) ");

        return sql.toString();
    }

    public static String getCreateTableEvento(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS EVENTO ( ");
        sql.append("    CODIGO   INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    TITULO     VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    DIA VARCHAR(250) NOT NULL DEFAULT(''), ");
        sql.append("    HORA    VARCHAR(200) NOT NULL DEFAULT(''), ");
        sql.append("    FACILITADOR    VARCHAR(200) NOT NULL DEFAULT(''), ");
        sql.append("    DESCRICAO VARCHAR(20)  NOT NULL DEFAULT('') ) ");

        return sql.toString();
    }
}
