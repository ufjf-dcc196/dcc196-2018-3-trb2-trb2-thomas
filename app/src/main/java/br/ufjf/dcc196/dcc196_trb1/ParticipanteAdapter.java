package br.ufjf.dcc196.dcc196_trb1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.ParticipanteContract;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolderParticipante> {
    private List<Participante> dados;
    private Cursor cursor;


    public ParticipanteAdapter(List<Participante> dados) {
        this.dados = dados;
    }

    public ParticipanteAdapter(Cursor cursor){
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ParticipanteAdapter.ViewHolderParticipante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.lista_participante, parent, false);

        ViewHolderParticipante holderParticipante = new ViewHolderParticipante(view, parent.getContext());

        return holderParticipante;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipanteAdapter.ViewHolderParticipante holder, int position) {
        int indexNome = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME);
        int indexId = cursor.getColumnIndexOrThrow(ParticipanteContract.Participante._ID);
        cursor.moveToPosition(position);
        holder.txt_nome.setText(cursor.getString(indexNome));
    }


    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolderParticipante extends RecyclerView.ViewHolder {

        public TextView txt_nome;

        public ViewHolderParticipante(View itemView, final Context context) {
            super(itemView);
            txt_nome = (TextView) itemView.findViewById(R.id.txt_nome_participante_lista);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(dados.size()>0) {

                        Participante participante = dados.get(getLayoutPosition());
                        //Toast.makeText(context, "Participante: " + participante.getNome(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, DetalhesParticipanteActivity.class);
                        intent.putExtra("POSICAO_PARTICIPANTE", getLayoutPosition());
                        intent.putExtra("PARTICIPANTE", participante);
                        ((AppCompatActivity) context).startActivityForResult(intent, MainActivity.REQUEST_ALTERA_DADOS_PARTICIPANTE);
                    }
                }
            });

        }
    }
}
