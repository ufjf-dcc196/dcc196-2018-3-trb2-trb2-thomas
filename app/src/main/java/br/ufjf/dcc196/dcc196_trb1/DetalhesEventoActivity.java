package br.ufjf.dcc196.dcc196_trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetalhesEventoActivity extends AppCompatActivity {
    TextView edt_titulo;
    TextView edt_hora;
    TextView edt_dia;
    TextView edt_facilitador;
    TextView edt_descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        edt_titulo = (TextView) findViewById(R.id.txt_titulo_detalhe_evento);
        edt_hora = (TextView) findViewById(R.id.txt_hora_detalhe_evento);
        edt_dia = (TextView) findViewById(R.id.txt_data_detalhe_evento);
        edt_facilitador = (TextView) findViewById(R.id.txt_facilitador_detalhe_evento);
        edt_descricao = (TextView) findViewById(R.id.txt_descricao_detalhe_evento);

        verificaParametro();
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
