package br.ufjf.dcc196.dcc196_trb1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufjf.dcc196.dcc196_trb1.Database.DadosOpenHelper;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Evento;


public class CadastroEventoActivity extends AppCompatActivity {

    private EditText txt_titulo;
    private EditText txt_dia;
    private EditText txt_hora;
    private EditText txt_facilitador;
    private EditText txt_descricao;
    private Button btn_confirmar;

    //MODIFICAR NO FUTURO//

    private SQLiteDatabase connection;
    private DadosOpenHelper dadosOpenHelper;
    private ConstraintLayout layoutContentActivityCadEvento;
    private Evento evento;

    //MODIFICAR NO FUTURO//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        txt_titulo = (EditText) findViewById(R.id.txt_titulo_evento);
        txt_dia = (EditText) findViewById(R.id.txt_dia_evento);
        txt_hora = (EditText) findViewById(R.id.txt_hora_evento);
        txt_facilitador = (EditText) findViewById(R.id.txt_facilitador_evento);
        txt_descricao = (EditText) findViewById(R.id.txt_descricao_evento);
        layoutContentActivityCadEvento = (ConstraintLayout)findViewById(R.id.layoutContentActivityCadEvento);
        btn_confirmar = (Button) findViewById(R.id.btn_confirmar_evento);

        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultado = new Intent();
                String titulo = txt_titulo.getText().toString();
                String dia = txt_dia.getText().toString();
                String hora = txt_hora.getText().toString();
                String facilitador = txt_facilitador.getText().toString();
                String descricao = txt_descricao.getText().toString();

                resultado.putExtra("titulo_evento", titulo);
                resultado.putExtra("dia_evento", dia);
                resultado.putExtra("hora_evento", hora);
                resultado.putExtra("facilitador_evento", facilitador);
                resultado.putExtra("descricao_evento", descricao);
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });
    }
}
