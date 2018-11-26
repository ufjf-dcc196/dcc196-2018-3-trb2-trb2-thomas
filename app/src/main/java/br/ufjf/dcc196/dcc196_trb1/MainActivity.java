package br.ufjf.dcc196.dcc196_trb1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufjf.dcc196.dcc196_trb1.Database.DadosOpenHelper;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Evento;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.EventoContract;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.ParticipanteContract;


public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;
    public static final int REQUEST_DADOS_PARTICIPANTE = 3;
    public static final int REQUEST_DADOS_EVENTO = 4;
    public static final int REQUEST_ALTERA_DADOS_PARTICIPANTE = 5;
    public static final int REQUEST_INSCREVE_EVENTO= 6;
    public static final int REQUEST_PARTICIPANTES_INSCRITOS= 7;


    //CONEXOES E BANCO DE DADOS
    private SQLiteDatabase connection;
    private DadosOpenHelper dadosOpenHelper;

    private Button btn_cadastro_participante;
    private Button btn_cadastro_evento;
    private RecyclerView rclParticipantes;
    private RecyclerView rclEventos;
    private static ParticipanteAdapter participanteAdapter;
    private static EventoAdapter eventoAdapter;
    private ConstraintLayout layoutActivityMain;

    static ArrayList<Evento> eventosList = new ArrayList<>();
    static ArrayList<Participante> participantesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cadastro_participante = (Button) findViewById(R.id.btn_cadastro_participante);
        btn_cadastro_evento = (Button) findViewById(R.id.btn_cadastro_evento);
        rclParticipantes = (RecyclerView) findViewById(R.id.rclview_lista_participante);
        rclEventos = (RecyclerView) findViewById(R.id.rclview_lista_evento);
        layoutActivityMain = (ConstraintLayout) findViewById(R.id.layoutActivityMain);

        connection = new DadosOpenHelper(MainActivity.this).getWritableDatabase();

        //criarConexao();
        //listaParticipantesBD();
        //listaEventosBD();

        btn_cadastro_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroParticipanteActivity.class);
                intent.putExtra("REQUEST_PARTICIPANTE_OU_ALTERA", MainActivity.REQUEST_PARTICIPANTE);
                startActivityForResult(intent, MainActivity.REQUEST_PARTICIPANTE);

            }
        });

        btn_cadastro_evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                startActivityForResult(intent, MainActivity.REQUEST_EVENTO);
            }
        });

        //OTIMIZA OS RECYCLERVIEWS//
        rclParticipantes.setHasFixedSize(true);
        rclEventos.setHasFixedSize(true);

        //CONFIGURAÇÃO DO RECYCLERVIEW PARTICIPANTES ACTIVITY MAIN//
        LinearLayoutManager linearLayoutManagerParticipante = new LinearLayoutManager(this);
        rclParticipantes.setLayoutManager(linearLayoutManagerParticipante);
        participanteAdapter = new ParticipanteAdapter(ParticipanteContract.getParticipanteCursor(connection,null,null));
        rclParticipantes.setAdapter(participanteAdapter);
        //CONFIGURAÇÃO DO RECYCLERVIEW PARTICIPANTES ACTIVITY MAIN//

        //CONFIGURAÇÃO DO RECYCLERVIEW EVENTOS ACTIVITY MAIN//
        LinearLayoutManager linearLayoutManagerEvento = new LinearLayoutManager(this);
        rclEventos.setLayoutManager(linearLayoutManagerEvento);
        //eventoAdapter = new EventoAdapter(EventoContract.getEventoCursor(connection,null,null));
        rclEventos.setAdapter(eventoAdapter);
        //CONFIGURAÇÃO DO RECYCLERVIEW EVENTOS ACTIVITY MAIN//

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {

            Bundle resultado = data.getExtras();

            //if (resultado.isEmpty()) {
            //    return;
            //}

            switch (requestCode) {
                case MainActivity.REQUEST_PARTICIPANTE:
                    String nome = resultado.getString("nome_participante");
                    String email = resultado.getString("email_participante");
                    String cpf = resultado.getString("cpf_participante");

                    ParticipanteContract.insere(connection, cpf, email, nome);
                    Toast.makeText(getApplicationContext(), "Participante Criado", Toast.LENGTH_SHORT).show();
                    participanteAdapter.notifyDataSetChanged();
                    break;
                case MainActivity.REQUEST_EVENTO:
                    String titulo = resultado.getString("titulo_evento");
                    String dia = resultado.getString("dia_evento");
                    String hora = resultado.getString("hora_evento");
                    String facilitador = resultado.getString("facilitador_evento");
                    String descricao = resultado.getString("descricao_evento");

                    //Evento evento = new Evento(titulo, dia, hora, facilitador, descricao);
                    //eventosList.add(evento);
                    EventoContract.inserir(connection, titulo, dia, hora, facilitador, descricao);
                    Toast.makeText(getApplicationContext(), "Evento Criado", Toast.LENGTH_SHORT).show();
                    eventoAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private void criarConexao(){
        try{

            dadosOpenHelper = new DadosOpenHelper(this);

            connection = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutActivityMain, R.string.message_conexao_criada_sucesso, Snackbar.LENGTH_SHORT).setAction("OK", null).show();

        }catch(SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }

    public static void alteraDadosParticipante(int posicao, String nome, String email, String cpf){
        participantesList.get(posicao).setNome(nome);
        participantesList.get(posicao).setEmail(email);
        participantesList.get(posicao).setCpf(cpf);

        participanteAdapter.notifyDataSetChanged();
    }
}

