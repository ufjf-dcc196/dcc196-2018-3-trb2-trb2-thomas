package br.ufjf.dcc196.dcc196_trb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_cadastro_participante = (Button) findViewById(R.id.btn_cadastro_participante);
        btn_cadastro_evento = (Button) findViewById(R.id.btn_cadastro_evento);

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
    }
}
