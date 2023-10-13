package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.controller.ItemPedidoController;
import com.example.forcavendasapp.controller.PedidoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;
import com.example.forcavendasapp.model.Item;
import com.example.forcavendasapp.model.ItemPedido;

import java.util.ArrayList;

public class PedidoVenda extends AppCompatActivity {

    private Spinner spSelCli;
    private Spinner spSelItem;
    private Spinner spSelEnd;
    private Button btRemover;
    private Button btAdicionar;
    private Button btCancel;
    private Button btCadastrar;
    private Button btGerPar;
    private ListView lvItensPedido;
    private EditText etQtItens;
    private EditText edQntPar;
    private RadioButton rbAVista;
    private RadioButton rbAPrazo;
    private ArrayList<Endereco> listaEnderecos;
    private ArrayList<Item> listaItem;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<ItemPedido> listItemPedido = new ArrayList<>();
    private int itemSelecionado = -1;
    private double vlrTotal = 0.0;
    private double vlrTotalVista = 0.0;
    private int qtdTotal = 0;
    private EnderecoController enderecoController = new EnderecoController(this);
    private ItemController itemController = new ItemController(this);
    private ClienteController clienteController = new ClienteController(this);
    private PedidoController pedidoController = new PedidoController(this);
    private ItemPedidoController itemPedidoController = new ItemPedidoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Código para trocar para outra tela (atividade)
                Intent intent = new Intent(PedidoVenda.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}