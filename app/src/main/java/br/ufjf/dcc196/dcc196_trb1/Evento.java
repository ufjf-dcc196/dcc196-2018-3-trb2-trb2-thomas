package br.ufjf.dcc196.dcc196_trb1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Serializable{
    private String titulo, dia, hora, facilitador, descricao;
    private ArrayList<Participante> participantesInscritos = new ArrayList<>();

    public ArrayList<Participante> getParticipantesInscritos() {
        return participantesInscritos;
    }

    public void inscreveNoEvento(Participante participante){this.participantesInscritos.add(participante);}
    public void cancelaInscricao(int position){ this.participantesInscritos.remove(position);}

    public Evento(String titulo, String dia, String hora, String facilitador, String descricao) {
        this.titulo = titulo;
        this.dia = dia;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public void setFacilitador(String facilitador) {
        this.facilitador = facilitador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
