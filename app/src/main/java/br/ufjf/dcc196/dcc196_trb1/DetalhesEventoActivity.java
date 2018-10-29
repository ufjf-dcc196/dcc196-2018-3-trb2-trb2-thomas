package br.ufjf.dcc196.dcc196_trb1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DetalhesEventoActivity extends AppCompatActivity {
    EditText edt_titulo;
    EditText edt_hora;
    EditText edt_dia;
    EditText edt_facilitador;
    EditText edt_descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        edt_titulo = (EditText)findViewById(R.id.edt_titulo_detalhe_evento);
        edt_hora = (EditText)findViewById(R.id.edt_hora_detalhe_evento);
        edt_dia = (EditText)findViewById(R.id.edt_data_detalhe_evento);
        edt_facilitador = (EditText)findViewById(R.id.edt_facilitador_detalhe_evento);
        edt_descricao = (EditText)findViewById(R.id.edt_descricao_detalhe_evento);

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
