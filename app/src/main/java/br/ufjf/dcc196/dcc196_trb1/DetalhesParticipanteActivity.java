package br.ufjf.dcc196.dcc196_trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

public class DetalhesParticipanteActivity extends AppCompatActivity {
    private EditText txt_nome;
    private EditText txt_email;
    private EditText txt_cpf;
    private Button btn_altera;
    private Button btn_inscreve;
    private RecyclerView rcl_list_eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_participante);
        txt_nome = (EditText)findViewById(R.id.txt_nome_detalhe_participante);
        txt_email = (EditText)findViewById(R.id.txt_email_detalhe_participante);
        txt_cpf = (EditText)findViewById(R.id.txt_cpf_detalhe_participante);

        verificaParametro();
    }

    private void verificaParametro(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null && bundle.containsKey("PARTICIPANTE")){

            Participante participante = (Participante)bundle.getSerializable("PARTICIPANTE");
            txt_nome.setText(participante.getNome());
            txt_email.setText(participante.getEmail());
            txt_cpf.setText(participante.getCpf());
        }
    }
}
