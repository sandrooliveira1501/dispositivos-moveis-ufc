package br.ufc.trabalhocomponentesbasicos.model;

/**
 * Created by alexsandro on 17/11/15.
 */
public class Link {

    private String url;
    private String descricao;

    public Link(){}

    public Link(String url, String descricao){
        this();
        this.url = url;
        this.descricao = descricao;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String toString(){
        return this.descricao + " : " + this.url;
    }

}
