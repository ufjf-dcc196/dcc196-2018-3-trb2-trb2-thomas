package br.ufjf.dcc196.dcc196_trb1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Evento;

public class EventosInscritosAdapter extends RecyclerView.Adapter<EventosInscritosAdapter.ViewHolderEventosInscritos> {
    private List<Evento> dados;

    public EventosInscritosAdapter(List<Evento> dados) {
        this.dados = dados;
    }


    @NonNull
    @Override
    public EventosInscritosAdapter.ViewHolderEventosInscritos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lista_eventos, parent, false);
        ViewHolderEventosInscritos holderEventosInscritos = new ViewHolderEventosInscritos(view, parent.getContext());
        return holderEventosInscritos;
    }

    @Override
    public void onBindViewHolder(@NonNull EventosInscritosAdapter.ViewHolderEventosInscritos holder, int position) {
        if (dados != null && dados.size() > 0) {
            Evento evento = dados.get(position);
            holder.txt_evento.setText(evento.getTitulo());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderEventosInscritos extends RecyclerView.ViewHolder {
        public TextView txt_evento;

        public ViewHolderEventosInscritos(View itemView, final Context context) {
            super(itemView);
            txt_evento = (TextView) itemView.findViewById(R.id.txt_titulo_evento_lista);
        }
    }
}
