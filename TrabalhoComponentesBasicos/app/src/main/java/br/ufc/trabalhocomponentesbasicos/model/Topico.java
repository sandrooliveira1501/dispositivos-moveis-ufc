package br.ufc.trabalhocomponentesbasicos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexsandro on 17/11/15.
 */
public class Topico {

    private String nome;
    private List<Link> links;
    private String categoria;

    public Topico(){
        this.links = new ArrayList<>();
    }

    public Topico(String nome, String categoria){
        this();
        this.nome = nome;
        this.categoria = categoria;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public String getCategoria(){
        return this.categoria;
    }

    public void addLink(Link link){
        this.links.add(link);
    }

    public void setLinks(List<Link> links){
        this.links = links;
    }

    public List<Link> getLinks(){
        return this.links;
    }

    @Override
    public String toString(){
        return this.nome;
    }

    @Override
    public boolean equals(Object object) {
        if ( this == object ) return true;

        if ( !(object instanceof Topico) ) return false;
        Topico topico = (Topico) object;

        return topico.getNome().toLowerCase().equals(this.getNome().toLowerCase());
    }
}
