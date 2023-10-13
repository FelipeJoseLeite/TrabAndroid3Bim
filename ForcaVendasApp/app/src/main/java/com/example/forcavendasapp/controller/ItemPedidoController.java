package com.example.forcavendasapp.controller;

import android.content.Context;

import com.example.forcavendasapp.dao.ItemPedidoDao;
import com.example.forcavendasapp.model.ItemPedido;

import java.util.ArrayList;

public class ItemPedidoController {

    private Context context;

    public ItemPedidoController(Context context) {
        this.context = context;
    }

    public long salvarPedidoItem(ItemPedido itemPedido){
        return ItemPedidoDao.getInstancia(context).insert(itemPedido);
    }

    public long atualizarPedidoItem(ItemPedido itemPedido){
        return ItemPedidoDao.getInstancia(context).update(itemPedido);
    }

    public long apagarPedidoItem(ItemPedido itemPedido){
        return ItemPedidoDao.getInstancia(context).delete(itemPedido);
    }

    public ArrayList<ItemPedido> findAllPedidoItem(){
        return ItemPedidoDao.getInstancia(context).getAll();
    }

    public ItemPedido findByIdPedidoItem(int codigo) {
        return ItemPedidoDao.getInstancia(context).getById(codigo);
    }

    public String validaPedidoItem(int codigo, int codigoItem, int quantidade) {
        String mensagem = "";

        if (codigo <= 0) {
            mensagem += "Código do item do pedido deve ser maior que zero!\n";
        }

        if (codigoItem <= 0) {
            mensagem += "Código do item deve ser maior que zero!\n";
        }

        if (quantidade <= 0) {
            mensagem += "A quantidade do item deve ser maior que zero!\n";
        }

        return mensagem;
    }
}
