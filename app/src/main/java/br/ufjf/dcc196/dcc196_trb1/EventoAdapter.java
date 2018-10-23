package br.ufjf.dcc196.dcc196_trb1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderEvento>{

    private List<Evento> dados;

    public EventoAdapter(List<Evento> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderEvento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.lista_eventos, parent, false);
        ViewHolderEvento holderEvento = new ViewHolderEvento(view);

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderEvento holder, int position) {
        if(dados != null && dados.size()>0){
            Evento evento = dados.get(position);
            holder.txt_evento.setText(evento.getTitulo());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderEvento extends RecyclerView.ViewHolder{

        public TextView txt_evento;

        public ViewHolderEvento(View itemView) {
            super(itemView);

            txt_evento = (TextView)itemView.findViewById(R.id.txt_titulo_evento_lista);
        }
    }
}
