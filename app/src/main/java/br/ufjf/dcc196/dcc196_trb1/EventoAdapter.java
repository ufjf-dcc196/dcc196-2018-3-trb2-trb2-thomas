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

import br.ufjf.dcc196.dcc196_trb1.Dominio.Entidades.Evento;
import br.ufjf.dcc196.dcc196_trb1.Dominio.Repositorio.EventoContract;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolderEvento>{
    private Cursor cursor;
    private List<Evento> dados;

    public EventoAdapter(List<Evento> dados) {
        this.dados = dados;
    }

    public EventoAdapter(Cursor cursor){
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public EventoAdapter.ViewHolderEvento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.lista_eventos, parent, false);
        ViewHolderEvento holderEvento = new ViewHolderEvento(view, parent.getContext());

        return holderEvento;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoAdapter.ViewHolderEvento holder, int position) {
        int indexTitulo = cursor.getColumnIndexOrThrow(EventoContract.Evento.COLUMN_NAME_TITULO);
        int indexId = cursor.getColumnIndexOrThrow(EventoContract.Evento._ID);
        cursor.moveToPosition(position);
        holder.txt_evento.setText(cursor.getString(indexTitulo));
        final int id = cursor.getInt(indexId);
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderEvento extends RecyclerView.ViewHolder{

        public TextView txt_evento;

        public ViewHolderEvento(View itemView, final Context context) {
            super(itemView);
            txt_evento = (TextView)itemView.findViewById(R.id.txt_titulo_evento_lista);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dados.size()>0){
                        Evento evento = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, DetalhesEventoActivity.class);
                        intent.putExtra("POSICAO_EVENTO", getLayoutPosition());
                        intent.putExtra("EVENTO", evento);
                        ((AppCompatActivity)context).startActivityForResult(intent,4);
                    }
                }
            });

        }
    }
}
