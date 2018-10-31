package br.ufjf.dcc196.dcc196_trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import static br.ufjf.dcc196.dcc196_trb1.MainActivity.eventosList;
import static br.ufjf.dcc196.dcc196_trb1.MainActivity.participantesList;

public class DetalhesEventoActivity extends AppCompatActivity {
    TextView edt_titulo;
    TextView edt_hora;
    TextView edt_dia;
    TextView edt_facilitador;
    TextView edt_descricao;
    RecyclerView rcl_lista_participantes;
    ParticipantesEventoAdapter participantesEventoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        edt_titulo = (TextView) findViewById(R.id.txt_titulo_detalhe_evento);
        edt_hora = (TextView) findViewById(R.id.txt_hora_detalhe_evento);
        edt_dia = (TextView) findViewById(R.id.txt_data_detalhe_evento);
        edt_facilitador = (TextView) findViewById(R.id.txt_facilitador_detalhe_evento);
        edt_descricao = (TextView) findViewById(R.id.txt_descricao_detalhe_evento);
        rcl_lista_participantes = (RecyclerView)findViewById(R.id.rcl_lista_participantes_evento);

        verificaParametro();

        rcl_lista_participantes.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManagerLista = new LinearLayoutManager(this);
        rcl_lista_participantes.setLayoutManager(linearLayoutManagerLista);
        int posicao = getIntent().getExtras().getInt("POSICAO_EVENTO");
        participantesEventoAdapter = new ParticipantesEventoAdapter(eventosList.get(posicao).getParticipantesInscritos());
        rcl_lista_participantes.setAdapter(participantesEventoAdapter);
        participantesEventoAdapter.notifyDataSetChanged();
    }
    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null && bundle.containsKey("EVENTO")){
            Evento evento = (Evento)bundle.getSerializable("EVENTO");
            edt_titulo.setText(evento.getTitulo());
            edt_hora.setText(evento.getHora());
            edt_dia.setText(evento.getDia());
            edt_facilitador.setText(evento.getFacilitador());
            edt_descricao.setText(evento.getDescricao());
        }
    }
}
