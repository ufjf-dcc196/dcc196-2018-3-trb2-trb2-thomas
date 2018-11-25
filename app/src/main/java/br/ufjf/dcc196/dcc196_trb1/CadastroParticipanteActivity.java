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
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufjf.dcc196.dcc196_trb1.Database.DadosOpenHelper;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.ParticipanteRepositorio;

public class CadastroParticipanteActivity extends AppCompatActivity {

    private EditText txt_nome;
    private EditText txt_email;
    private EditText txt_cpf;
    private Button botao_confirmar;

    //MODIFICAR NO FUTURO//
    private ParticipanteRepositorio participanteRepositorio;
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

        criarConexao();
        editaDados();
        botao_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultado = new Intent();

                confirmar();

                //resultado.putExtra("nome_participante", nome);
                //resultado.putExtra("email_participante", email);
                //resultado.putExtra("cpf_participante", cpf);
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });
    }

    private void confirmar() {

        participante = new Participante();
        if (validaCampos() == false) {
            try {
                participanteRepositorio.inserir(participante);
            } catch (SQLException ex) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();
            }
        }
    }

    private boolean validaCampos() {
        boolean res = false;

        String nome = txt_nome.getText().toString();
        String email = txt_email.getText().toString();
        String cpf = txt_cpf.getText().toString();

        participante.setNome(nome);
        participante.setEmail(email);
        participante.setCpf(cpf);


        if (res == isCampoVazio(nome)) {
            txt_nome.requestFocus();
        } else if (res == isCampoVazio(cpf)) {
            txt_cpf.requestFocus();
        } else if (res == !isEmailValido(email)) {
            txt_email.requestFocus();
        }
        if (res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.message_campos_invalidos);
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
        return res;
    }

    //MODIFICAR NO FUTURO//
    private void criarConexao() {
        try {

            dadosOpenHelper = new DadosOpenHelper(this);

            connection = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(layoutContentActivityCadParti, R.string.message_conexao_criada_sucesso, Snackbar.LENGTH_SHORT).setAction("OK", null).show();

            participanteRepositorio = new ParticipanteRepositorio(connection);

        } catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }
    }
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

