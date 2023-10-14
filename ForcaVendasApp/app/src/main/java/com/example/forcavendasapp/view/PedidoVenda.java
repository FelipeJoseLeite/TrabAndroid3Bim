package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.ClienteAdapter;
import com.example.forcavendasapp.adapter.EnderecoAdapter;
import com.example.forcavendasapp.adapter.ItemAdapter;
import com.example.forcavendasapp.adapter.ItemPedidoAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.controller.ItemPedidoController;
import com.example.forcavendasapp.controller.PedidoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;
import com.example.forcavendasapp.model.Item;
import com.example.forcavendasapp.model.ItemPedido;
import com.example.forcavendasapp.model.Pedido;

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
    private ListView tvItensPedido;
    private EditText etQtItens;
    private EditText etQntPar;
    private TextView tvParcelas;
    private TextView tvPar;
    private TextView tvValorTotal;
    private TextView tVTotalItens;
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
    private String id_cliente;
    private EnderecoController enderecoController = new EnderecoController(this);
    private ItemController itemController = new ItemController(this);
    private ClienteController clienteController = new ClienteController(this);
    private PedidoController pedidoController = new PedidoController(this);
    private ItemPedidoController itemPedidoController = new ItemPedidoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_venda);

        btCadastrar = findViewById(R.id.btCadastrar);
        btCancel = findViewById(R.id.btCancel);
        btAdicionar = findViewById(R.id.btAdicionar);
        btRemover = findViewById(R.id.btRemover);
        btGerPar = findViewById(R.id.btGerPar);
        spSelEnd = findViewById(R.id.spSelEnd);
        spSelItem = findViewById(R.id.spSelItem);
        spSelCli = findViewById(R.id.spSelCli);
        etQntPar = findViewById(R.id.etQntPar);
        etQtItens = findViewById(R.id.etQtItens);
        rbAPrazo = findViewById(R.id.rbAPrazo);
        rbAVista = findViewById(R.id.rbAVista);
        tvItensPedido = findViewById(R.id.tvItensPedido);
        tvParcelas = findViewById(R.id.tvParcelas);
        tvPar = findViewById(R.id.tvPar);
        tvValorTotal = findViewById(R.id.tvValorTotal);
        tVTotalItens = findViewById(R.id.tVTotalItens);

        listaEnderecos = enderecoController.findAllEndereco();
        EnderecoAdapter adapter = new EnderecoAdapter(this, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSelEnd.setAdapter(adapter);

        listaCliente = clienteController.findAllCliente();
        ClienteAdapter adapterCli = new ClienteAdapter(this, listaCliente);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSelCli.setAdapter(adapterCli);

        listaItem = itemController.findAllItem();
        ItemAdapter adapterItem = new ItemAdapter(this, listaItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSelItem.setAdapter(adapterItem);

        ItemPedidoAdapter itemPedidoAdapter = new ItemPedidoAdapter(this, listItemPedido);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PedidoVenda.this, MainActivity.class);
                startActivity(intent);
            }
        });
        spSelCli.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Cliente cliente = (Cliente) adapterView.getItemAtPosition(i);
                id_cliente = cliente.getNome();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
               public void onClick(View v) {
                  Pedido pedido = new Pedido();
                  Endereco end = (Endereco) spSelEnd.getSelectedItem();
                  Cliente cli = (Cliente) spSelCli.getSelectedItem();
                  pedido.setCodPessoa((int) cli.getCodigo());
                  pedido.setVlrTotal(vlrTotal);
                  pedido.setCodEndereco(end.getCodigo());
                  int pedidoid = 1 + pedidoController.findAllPedido().size();
                  for (int i = 0; i < listItemPedido.size(); i++) {
                      listItemPedido.get(i).setCodigoPedido(pedidoid);
                      itemPedidoController.salvarPedidoItem(listItemPedido.get(i));
                }
                pedidoController.salvarPedido(pedido);
                limpaCampos();
                Toast.makeText(PedidoVenda.this, "Pedido salvo com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PedidoVenda.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tvItensPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelecionado = position;
            }
        });
        rbAPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvParcelas.setVisibility(View.VISIBLE);
                etQntPar.setVisibility(View.VISIBLE);
                btGerPar.setVisibility(View.VISIBLE);
                tvPar.setVisibility(View.VISIBLE);
                vlrTotal = vlrTotal + (vlrTotal * 0.05);
                tvValorTotal.setText("Valor Total: " + vlrTotal);
            }
        });
        rbAVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvParcelas.setVisibility(View.GONE);
                etQntPar.setVisibility(View.GONE);
                btGerPar.setVisibility(View.GONE);
                tvParcelas.setVisibility(View.GONE);
                tvPar.setVisibility(View.GONE);
                vlrTotal = vlrTotalVista;
                vlrTotal = vlrTotal - (vlrTotal * 0.05);
                tvValorTotal.setText("Valor Total: " + vlrTotal);
            }
        });
        btGerPar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qntParcelas = 0;
                Double vlrParcelas;
                String parcelas = "";
                if (!etQntPar.getText().toString().equals("")) {

                    qntParcelas = Integer.parseInt(etQntPar.getText().toString());

                    vlrParcelas = vlrTotal / qntParcelas;

                    for (int i = 0; i < qntParcelas; i++) {
                        parcelas += "Parcela " + (i + 1) + " - R$ " + String.format("%.2f", vlrParcelas) + "\n";
                    }
                    tvPar.setText(parcelas);
                }
            }
        });

        spSelEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Endereco end = (Endereco) spSelEnd.getSelectedItem();
                if (!end.getCidade().toUpperCase().equals("TOLEDO")) {
                    vlrTotal = vlrTotal + 20.00;
                    tvValorTotal.setText("Valor Total: " + vlrTotal);
                } else if (!end.getCidade().toUpperCase().equals("TOLEDO") && !end.getUf().toUpperCase().equals("PR")) {
                    vlrTotal = vlrTotal + 50.00;
                    tvValorTotal.setText("Valor Total: " + vlrTotal);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemPedido pd = new ItemPedido();
                Item item = (Item) spSelItem.getSelectedItem();
                if (etQtItens.getText().toString().equals("0") || etQtItens.getText().toString().isEmpty()) {
                    Toast.makeText(PedidoVenda.this, "Por favor, preencha o campo de quantidade", Toast.LENGTH_SHORT).show();
                } else {
                    boolean itemJaExiste = false;
                    for (ItemPedido pedidoItem : listItemPedido) {
                        if (pedidoItem.getCodigoItem() == item.getCodigo()) {
                            itemJaExiste = true;
                            break;
                        }
                    }
                    if (itemJaExiste) {
                        Toast.makeText(PedidoVenda.this, "Item já está na lista", Toast.LENGTH_SHORT).show();
                    } else {
                        pd.setQuantidade(Integer.parseInt(etQtItens.getText().toString()));
                        pd.setCodigoItem(item.getCodigo());
                        pd.setDesc(item.getDescricao());
                        vlrTotal += item.getVlrUnit() * pd.getQuantidade();
                        vlrTotalVista += item.getVlrUnit() * pd.getQuantidade();
                        tvValorTotal.setText("Valor Total: " + vlrTotal);
                        qtdTotal += pd.getQuantidade();
                        tVTotalItens.setText("Total de Itens: " + qtdTotal);
                        listItemPedido.add(pd);
                        tvItensPedido.setAdapter(itemPedidoAdapter);
                    }
                }
            }
        });
        btRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelecionado != -1) {
                    if (itemSelecionado < itemPedidoAdapter.getCount()) {
                        qtdTotal -= itemPedidoAdapter.getItem(itemSelecionado).getQuantidade();
                        tvValorTotal.setText("Valor Total: " + vlrTotal);
                        itemPedidoAdapter.remove(itemPedidoAdapter.getItem(itemSelecionado));
                        itemPedidoAdapter.notifyDataSetChanged();
                    } else {

                        Toast.makeText(PedidoVenda.this, "Nenhum item selecionado!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void limpaCampos() {
        vlrTotal = 0;
        qtdTotal = 0;
        etQntPar.setText("0");
        rbAVista.setChecked(true);
        rbAPrazo.setChecked(false);
        etQntPar.setText("0");
        listItemPedido.clear();
    }
}