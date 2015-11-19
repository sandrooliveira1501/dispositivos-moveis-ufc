package br.ufc.trabalhocomponentesbasicos.dao;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.model.Link;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * Created by alexsandro on 17/11/15.
 */
public interface TopicoDAO {

    String SEM_TOPICO = "Sem Tópico";

    void addTopico(Topico topico, boolean padrao);
    List<Topico> getTopicos();
    List<Link> getLinks();
    void addLink(Link link, Topico topico);
    void addLink(Link link);
    Topico getTopico(String nome);
    Topico getTopicoPadrao();

}
