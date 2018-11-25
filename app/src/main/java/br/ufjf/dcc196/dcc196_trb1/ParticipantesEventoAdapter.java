package br.ufjf.dcc196.dcc196_trb1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Participante;

public class ParticipantesEventoAdapter extends RecyclerView.Adapter<ParticipantesEventoAdapter.ViewHolderParticipantesEvento>{
    private List<Participante> dados;

    public ParticipantesEventoAdapter(List<Participante> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ParticipantesEventoAdapter.ViewHolderParticipantesEvento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lista_eventos, parent, false);
        ViewHolderParticipantesEvento holderParticipantesEvento = new ViewHolderParticipantesEvento(view, parent.getContext());
        return holderParticipantesEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantesEventoAdapter.ViewHolderParticipantesEvento holder, int position) {
        if(dados != null && dados.size()>0){
            Participante participante = dados.get(position);
            holder.txt_nome.setText(participante.getNome());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderParticipantesEvento extends RecyclerView.ViewHolder {
        public TextView txt_nome;

        public ViewHolderParticipantesEvento(View itemView, final Context context) {
            super(itemView);
            txt_nome = (TextView)itemView.findViewById(R.id.txt_nome_participante_lista);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dados.size()>0){
                        Participante participante= dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, DetalhesEventoActivity.class);
                        intent.putExtra("PARTICIPANTE_INSCRITO", participante);
                        ((AppCompatActivity)context).startActivityForResult(intent,MainActivity.REQUEST_PARTICIPANTES_INSCRITOS);
                    }
                }
            });
        }
    }
}