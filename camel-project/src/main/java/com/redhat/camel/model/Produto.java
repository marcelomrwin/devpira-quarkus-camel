package com.redhat.camel.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Produto {
    private String codigo;
    private String descricao;
    private Double valor;

    public Produto() {
		super();		
    }
    
    public Produto(String codigo,String descricao,Double valor){
        this();
        this.codigo=codigo;
        this.descricao=descricao;
        this.valor=valor;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
}
