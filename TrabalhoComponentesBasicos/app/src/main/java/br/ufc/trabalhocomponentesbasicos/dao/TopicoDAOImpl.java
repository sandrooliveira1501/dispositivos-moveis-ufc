package br.ufc.trabalhocomponentesbasicos.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufc.trabalhocomponentesbasicos.model.Link;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * Created by alexsandro on 17/11/15.
 */
public class TopicoDAOImpl implements TopicoDAO{

    private static TopicoDAOImpl topicoDAO = null;
    private static Topico semTopico = null;
    private static Topico topicoPadrao = null;

    private static List<Topico> topicos = new ArrayList<>();

    public static TopicoDAOImpl newInstance(){

        if ( topicoDAO == null ){
            topicoDAO = new TopicoDAOImpl();
            semTopico = new Topico(SEM_TOPICO, null);
            topicoPadrao = null;
            topicoDAO.addTopico(semTopico, false);
        }

        return topicoDAO;
    }

    @Override
    public void addTopico(Topico topico, boolean padrao) {
        topicos.add(topico);
        if(padrao){
            topicoPadrao = topico;
        }
    }

    @Override
    public void addLink(Link link, Topico topico){
        if(topicos.contains(topico)){
        }else{
            addTopico(topico, false);
        }
        topico.addLink(link);
        Log.i("teste", topicos.size()+ "");
        Log.i("teste", topico.getNome());

    }

    @Override
    public void addLink(Link link) {
        semTopico.addLink(link);
    }

    @Override
    public List<Topico> getTopicos() {
        return topicos;
    }

    public List<Link> getLinks(){
        List<Link> links = new ArrayList<>();

        for(Topico topico : topicos){
            links.addAll(topico.getLinks());
        }

        return links;
    }

    @Override
    public Topico getTopico(String nome) {
        for(Topico topico : topicos){
            if(topico.getNome().toLowerCase().equals(nome.toLowerCase()))
                return topico;

        }
        return null;
    }

    @Override
    public Topico getTopicoPadrao() {
        return topicoPadrao;
    }
}
