package br.ufc.trabalhocomponentesbasicos.controller;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.dao.TopicoDAO;
import br.ufc.trabalhocomponentesbasicos.dao.TopicoDAOImpl;
import br.ufc.trabalhocomponentesbasicos.model.Link;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * Created by alexsandro on 18/11/15.
 */
public class ControllerLinkImpl implements ControllerLink {

    private TopicoDAO topicoDAO;

    public ControllerLinkImpl(){
        topicoDAO = TopicoDAOImpl.newInstance();
    }

    @Override
    public List<Link> getLinks() {
        return topicoDAO.getLinks();
    }

    @Override
    public void addLink(Link link) {
        topicoDAO.addLink(link);
    }

    @Override
    public void addLink(Link link, Topico topico) {
        topicoDAO.addLink(link, topico);
    }
}
