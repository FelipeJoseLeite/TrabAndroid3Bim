package com.example.forcavendasapp.model;

import java.util.Date;

public class Cliente {

    private Integer codigo;
    private String nome;
    private String cpf;
    private String dtNasc;
    private Integer codEndereco;

    public Cliente(Integer codigo, String nome, String cpf, String dtNasc, Integer codEndereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.codEndereco = codEndereco;
    }

    public Cliente() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public Integer getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(Integer codEndereco) {
        this.codEndereco = codEndereco;
    }
}
