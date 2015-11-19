package br.ufc.trabalhocomponentesbasicos.controller;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.model.Link;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * Created by alexsandro on 18/11/15.
 */
public interface ControllerLink {

    List<Link> getLinks();

    void addLink(Link link, Topico topico);
    void addLink(Link link);

}

