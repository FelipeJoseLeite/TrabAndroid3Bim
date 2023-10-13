package com.example.forcavendasapp.model;

import java.util.List;

public class Pedido {

    private int codigo;
    private int codPessoa;
    private int codEndereco;
    private double vlrTotal;

    public Pedido() {
    }

    public Pedido(int codigo, int codPessoa, int codEndereco, double vlrTotal) {
        this.codigo = codigo;
        this.codPessoa = codPessoa;
        this.codEndereco = codEndereco;
        this.vlrTotal = vlrTotal;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public double getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(double vlrTotal) {
        this.vlrTotal = vlrTotal;
    }
}
