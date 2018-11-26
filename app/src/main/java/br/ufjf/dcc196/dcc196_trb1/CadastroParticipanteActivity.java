package br.ufjf.dcc196.dcc196_trb1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufjf.dcc196.dcc196_trb1.Database.DadosOpenHelper;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText txt_nome;
    private EditText txt_email;
    private EditText txt_cpf;
    private Button botao_confirmar;

    //MODIFICAR NO FUTURO//
    private SQLiteDatabase connection;
    private DadosOpenHelper dadosOpenHelper;
    private ConstraintLayout layoutContentActivityCadParti;
    private Participante participante;

    //MODIFICAR NO FUTURO//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);
        txt_nome = (EditText) findViewById(R.id.txt_nome_participante);
        txt_email = (EditText) findViewById(R.id.txt_email_participante);
        txt_cpf = (EditText) findViewById(R.id.txt_cpf_participante);
        botao_confirmar = (Button) findViewById(R.id.btn_confirmar_participante);

        layoutContentActivityCadParti = (ConstraintLayout) findViewById(R.id.layoutContentActivityCadParti);

        editaDados();


        botao_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultado = new Intent();

                //confirmar();
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

    //MODIFICAR NO FUTURO//

    //MODIFICAR NO FUTURO//

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    private boolean isEmailValido(String email) {
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    private void editaDados() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getInt("REQUEST_PARTICIPANTE_OU_ALTERA") == MainActivity.REQUEST_ALTERA_DADOS_PARTICIPANTE) {
            txt_nome.setText(bundle.get("NOVO_NOME").toString());
            txt_email.setText(bundle.get("NOVO_EMAIL").toString());
            txt_cpf.setText(bundle.get("NOVO_CPF").toString());
        }
    }
}

