package br.ufjf.dcc196.dcc196_trb1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ParticipanteAdapter extends RecyclerView.Adapter<ParticipanteAdapter.ViewHolderParticipante> {
    private List<Participante> dados;


    public ParticipanteAdapter(List<Participante> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ParticipanteAdapter.ViewHolderParticipante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.lista_participante, parent, false);

        ViewHolderParticipante holderParticipante = new ViewHolderParticipante(view);

        return holderParticipante;
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipanteAdapter.ViewHolderParticipante holder, int position) {
        if (dados != null && dados.size() > 0) {
            Participante participante = dados.get(position);
            holder.txt_nome.setText(participante.getNome());
        }

    }


    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderParticipante extends RecyclerView.ViewHolder {

        public TextView txt_nome;

        public ViewHolderParticipante(View itemView) {
            super(itemView);

            txt_nome = (TextView) itemView.findViewById(R.id.txt_nome_participante_lista);
        }
    }
}
