package br.ufc.trabalhocomponentesbasicos.controller;

import java.util.List;

import br.ufc.trabalhocomponentesbasicos.model.Topico;

/**
 * Created by alexsandro on 18/11/15.
 */
public interface ControllerTopico {

    String CATEGORIAS[] = {"Sem Categoria", "Blog", "Notícia", "Educação", "Trabalho"};

    List<Topico> getTopicos();
    void addTopico(Topico topico, boolean padrao);
    Topico getTopico(String nomeTopico);
    Topico getTopicoPadrao();

}
