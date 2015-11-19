package br.ufc.trabalhocomponentesbasicos.controller;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.dao.TopicoDAO;
import br.ufc.trabalhocomponentesbasicos.dao.TopicoDAOImpl;
import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * Created by alexsandro on 18/11/15.
 */
public class ControllerTopicoImpl implements ControllerTopico {

    private TopicoDAO topicoDAO;

    public ControllerTopicoImpl(){
        topicoDAO = TopicoDAOImpl.newInstance();
    }

    @Override
    public List<Topico> getTopicos() {
        return topicoDAO.getTopicos();
    }

    public void addTopico(Topico topico, boolean padrao){
        topicoDAO.addTopico(topico,padrao);
    }

    @Override
    public Topico getTopico(String nomeTopico) {
        return topicoDAO.getTopico(nomeTopico);
    }

    @Override
    public Topico getTopicoPadrao() {
        return topicoDAO.getTopicoPadrao();
    }
}
