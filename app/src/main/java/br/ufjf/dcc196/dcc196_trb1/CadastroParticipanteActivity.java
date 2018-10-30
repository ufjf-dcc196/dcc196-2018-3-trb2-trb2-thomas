package br.ufjf.dcc196.dcc196_trb1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText txt_nome;
    private EditText txt_email;
    private EditText txt_cpf;
    private Button botao_confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);
        txt_nome = (EditText) findViewById(R.id.txt_nome_participante);
        txt_email = (EditText) findViewById(R.id.txt_email_participante);
        txt_cpf = (EditText) findViewById(R.id.txt_cpf_participante);
        botao_confirmar = (Button) findViewById(R.id.btn_confirmar_participante);

        editaDados();
        botao_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultado = new Intent();
                String nome = txt_nome.getText().toString();
                String email = txt_email.getText().toString();
                String cpf = txt_cpf.getText().toString();
                resultado.putExtra("nome_participante", nome);
                resultado.putExtra("email_participante", email);
                resultado.putExtra("cpf_participante", cpf);
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });
    }
    private void editaDados(){
        Bundle bundle = getIntent().getExtras();
        if(bundle.getInt("REQUEST_PARTICIPANTE_OU_ALTERA") == MainActivity.REQUEST_ALTERA_DADOS_PARTICIPANTE) {
                txt_nome.setText(bundle.get("NOVO_NOME").toString());
                txt_email.setText(bundle.get("NOVO_EMAIL").toString());
                txt_cpf.setText(bundle.get("NOVO_CPF").toString());
            }
        }
    }

