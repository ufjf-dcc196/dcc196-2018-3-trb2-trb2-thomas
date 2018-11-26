package br.ufjf.dcc196.dcc196_trb1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Evento;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;

import static br.ufjf.dcc196.dcc196_trb1.MainActivity.eventosList;
import static br.ufjf.dcc196.dcc196_trb1.MainActivity.participantesList;

public class DetalhesParticipanteActivity extends AppCompatActivity {
    private TextView txt_nome;
    private TextView txt_email;
    private TextView txt_cpf;
    private Button btn_altera;
    private RecyclerView rcl_list_eventos_inscritos;
    private RecyclerView rcl_list_eventos_inscrever;
    private static EventosInscreverAdapter eventosInscreverAdapter;
    private static EventosInscritosAdapter eventosInscritosAdapter;

    Participante participante;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int posicao_participante = getIntent().getExtras().getInt("POSICAO_PARTICIPANTE");
        setContentView(R.layout.activity_detalhes_participante);
        txt_nome = (TextView) findViewById(R.id.txt_nome_detalhe_participante);
        txt_email = (TextView) findViewById(R.id.txt_email_detalhe_participante);
        txt_cpf = (TextView) findViewById(R.id.txt_cpf_detalhe_participante);
        btn_altera = (Button)findViewById(R.id.btn_altera_detalhe_participante);
        rcl_list_eventos_inscritos = (RecyclerView)findViewById(R.id.rcl_lista_eventos_inscritos_detalhe_participante);
        rcl_list_eventos_inscrever = (RecyclerView)findViewById(R.id.rcl_lista_eventos_inscrever_detalhe_participante);

        verificaParametro();

        btn_altera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalhesParticipanteActivity.this, CadastroParticipanteActivity.class);
                //String nome = txt_nome.getText().toString();
                //String email = txt_email.getText().toString();
               // String cpf = txt_cpf.getText().toString();
                //intent.putExtra("NOVO_NOME", nome);
                //intent.putExtra("NOVO_EMAIL", email);
                //intent.putExtra("NOVO_CPF", cpf);
                intent.putExtra("REQUEST_PARTICIPANTE_OU_ALTERA", MainActivity.REQUEST_ALTERA_DADOS_PARTICIPANTE);
                startActivityForResult(intent,MainActivity.REQUEST_ALTERA_DADOS_PARTICIPANTE);
            }
        });

        rcl_list_eventos_inscrever.setHasFixedSize(true);
        rcl_list_eventos_inscritos.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManagerInscrever = new LinearLayoutManager(this);
        rcl_list_eventos_inscrever.setLayoutManager(linearLayoutManagerInscrever);
        eventosInscreverAdapter = new EventosInscreverAdapter(eventosList);
        rcl_list_eventos_inscrever.setAdapter(eventosInscreverAdapter);

        LinearLayoutManager linearLayoutManagerInscritos = new LinearLayoutManager(this);
        rcl_list_eventos_inscritos.setLayoutManager(linearLayoutManagerInscritos);
        eventosInscritosAdapter = new EventosInscritosAdapter(participantesList.get(posicao_participante).getEventosInscritos());
        rcl_list_eventos_inscritos.setAdapter(eventosInscritosAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle resultado = getIntent().getExtras();

        switch(requestCode){
            case MainActivity.REQUEST_ALTERA_DADOS_PARTICIPANTE:
                //String nome = resultado.getString("nome_participante");
                //String email = resultado.getString("email_participante");
                //String cpf = resultado.getString("cpf_participante");

               // int posicao = resultado.getInt("POSICAO_PARTICIPANTE");
                //MainActivity.alteraDadosParticipante(posicao,nome, email, cpf);

               // txt_nome.setText(nome);
                //txt_email.setText(email);
                //txt_cpf.setText(cpf);
                Toast.makeText(getApplicationContext(), "Dados alterados.", Toast.LENGTH_SHORT).show();
                break;

                case MainActivity.REQUEST_INSCREVE_EVENTO:

                    int posicao_participante = resultado.getInt("POSICAO_PARTICIPANTE");
                    //int posicao_evento = resultado.getInt("EVENTO_INSCRITO");
                    Participante participante = participantesList.get(posicao_participante);
                    Evento evento = (Evento)resultado.getSerializable("EVENTO_INSCRITO");
                    participante.inscreveEmEvento(evento);
                    eventosInscritosAdapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Inscrito em evento.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
        }
    }


    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();
        participante = new Participante();
        if(bundle!=null && bundle.containsKey("PARTICIPANTE")){

            participante = (Participante)bundle.getSerializable("PARTICIPANTE");
            txt_nome.setText(participante.getNome());
            txt_email.setText(participante.getEmail());
            txt_cpf.setText(participante.getCpf());
        }
    }

}
