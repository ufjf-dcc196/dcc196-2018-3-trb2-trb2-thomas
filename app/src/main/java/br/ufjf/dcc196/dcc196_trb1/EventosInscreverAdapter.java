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

public class EventosInscreverAdapter extends RecyclerView.Adapter<EventosInscreverAdapter.ViewHolderEventosInscrever>{
    private List<Evento> dados;

    public EventosInscreverAdapter(List<Evento> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public EventosInscreverAdapter.ViewHolderEventosInscrever onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lista_eventos, parent, false);
        ViewHolderEventosInscrever holderEventosInscrever = new ViewHolderEventosInscrever(view, parent.getContext());
        return holderEventosInscrever;
    }

    @Override
    public void onBindViewHolder(@NonNull EventosInscreverAdapter.ViewHolderEventosInscrever holder, int position) {
        if(dados != null && dados.size()>0){
            Evento evento = dados.get(position);
            holder.txt_evento.setText(evento.getTitulo());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderEventosInscrever extends RecyclerView.ViewHolder {
        public TextView txt_evento;

        public ViewHolderEventosInscrever(View itemView, final Context context) {
            super(itemView);
            txt_evento = (TextView)itemView.findViewById(R.id.txt_titulo_evento_lista);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dados.size()>0){
                        Evento evento = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, DetalhesParticipanteActivity.class);
                        intent.putExtra("EVENTO_INSCRITO", evento);
                        ((AppCompatActivity)context).startActivityForResult(intent,MainActivity.REQUEST_INSCREVE_EVENTO);
                    }
                }
            });
        }
    }
}
