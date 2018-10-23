package br.ufjf.dcc196.dcc196_trb1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_PARTICIPANTE = 1;
    public static final int REQUEST_EVENTO = 2;
    public static final int REQUEST_DADOS_PARTICIPANTE = 3;
    public static final int REQUEST_DADOS_EVENTO = 4;
    public static final int REQUEST_ALTERA_DADOS_PARTICIPANTE = 5;

    private Button btn_cadastro_participante;
    private Button btn_cadastro_evento;
    private RecyclerView rclParticipantes;
    private RecyclerView rclEventos;
    private ParticipanteAdapter participanteAdapter;

    ArrayList<Evento> eventosList = new ArrayList<>();
    ArrayList<Participante> participantesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cadastro_participante = (Button) findViewById(R.id.btn_cadastro_participante);
        btn_cadastro_evento = (Button) findViewById(R.id.btn_cadastro_evento);
        rclParticipantes = (RecyclerView)findViewById(R.id.rclview_lista_participante);
        rclEventos = (RecyclerView)findViewById(R.id.rclview_lista_evento);

        btn_cadastro_participante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroParticipanteActivity.class);
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


        rclParticipantes.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManagerParticipante = new LinearLayoutManager(this);
        rclParticipantes.setLayoutManager(linearLayoutManagerParticipante);
        participanteAdapter = new ParticipanteAdapter(participantesList);
        rclParticipantes.setAdapter(participanteAdapter);

        //LinearLayoutManager linearLayoutManagerEvento = new LinearLayoutManager(this);
        //rclEventos.setLayoutManager(linearLayoutManagerEvento);
        //EventoAdapter = new EventoAdapter(eventosList);
        //rclEventos.setAdapter(eventosAdapter);

        listaParticipantesBD();
        listaEventosBD();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {

            Bundle resultado = data.getExtras();

            if (resultado.isEmpty()) {
                return;
            }

            switch (requestCode) {
                case MainActivity.REQUEST_PARTICIPANTE:
                    String nome = resultado.getString("nome_participante");
                    String email = resultado.getString("email_participante");
                    String cpf = resultado.getString("cpf_participante");

                    Participante participante = new Participante(nome,email,cpf);
                    participantesList.add(participante);
                    Toast.makeText(getApplicationContext(), "Participante Criado", Toast.LENGTH_SHORT).show();
                    break;
                case MainActivity.REQUEST_EVENTO:
                    String titulo = resultado.getString("titulo_evento");
                    String dia = resultado.getString("dia_evento");
                    String hora = resultado.getString("hora_evento");
                    String facilitador = resultado.getString("facilitador_evento");
                    String descricao = resultado.getString("descricao_evento");

                    Evento evento = new Evento(titulo,dia,hora,facilitador,descricao);
                    eventosList.add(evento);
                    Toast.makeText(getApplicationContext(),"Evento Criado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public void listaParticipantesBD(){
        participantesList.add(new Participante("Aluno1","aluno1@hotmail.com","11111111111"));
        participantesList.add(new Participante("Aluno2","aluno2@hotmail.com","22222222222"));
        participantesList.add(new Participante("Aluno3","aluno3@hotmail.com","33333333333"));
        participantesList.add(new Participante("Aluno4","aluno4@hotmail.com","44444444444"));
        participantesList.add(new Participante("Aluno5","aluno5@hotmail.com","55555555555"));
    }
    public void listaEventosBD(){
        eventosList.add((new Evento("Evento1","Dia 1","01:00","Professor1","Workshop1")));
        eventosList.add((new Evento("Evento2","Dia 2","02:00","Professor2","Workshop2")));
        eventosList.add((new Evento("Evento3","Dia 3","03:00","Professor3","Workshop3")));
        eventosList.add((new Evento("Evento4","Dia 4","04:00","Professor4","Workshop4")));
        eventosList.add((new Evento("Evento5","Dia 5","05:00","Professor5","Workshop5")));
    }
};

