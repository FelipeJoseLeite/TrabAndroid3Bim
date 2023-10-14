package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.adapter.EnderecoAdapter;
import com.example.forcavendasapp.controller.ClienteController;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Cliente;
import com.example.forcavendasapp.model.Endereco;

import java.util.ArrayList;

public class CadastroCliente extends AppCompatActivity {

    private EditText etNome;
    private EditText etCpf;
    private EditText etDtNasc;
    private EditText etCod;
    private Button btCancel;
    private Button btCadastrar;
    private Spinner spCodEnd;
    private ArrayList<Endereco> listaEnderecos;
    private ClienteController clienteController = new ClienteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        etCpf = findViewById(R.id.etCpf);
        etDtNasc = findViewById(R.id.etDtNasc);
        etNome = findViewById(R.id.etNome);
        etCod = findViewById(R.id.etCod);
        btCancel = findViewById(R.id.btCancel);
        btCadastrar = findViewById(R.id.btCadastrar);
        spCodEnd = findViewById(R.id.spCodEnd);

        EnderecoController enderecoController = new EnderecoController(this);

        listaEnderecos = enderecoController.findAllEndereco();
        EnderecoAdapter adapter = new EnderecoAdapter(this, listaEnderecos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCodEnd.setAdapter(adapter);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroCliente.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    Cliente cliente = new Cliente();
                    Endereco end = (Endereco) spCodEnd.getSelectedItem();
                    cliente.setNome(etNome.getText().toString());
                    cliente.setCpf(etCpf.getText().toString());
                    cliente.setDtNasc(etDtNasc.getText().toString());
                    if (end != null) {
                        cliente.setCodEndereco(end.getCodigo());
                        clienteController.salvarCliente(cliente);
                        limpaCampos();
                        Toast.makeText(CadastroCliente.this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CadastroCliente.this, "Selecione um endereço!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroCliente.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(CadastroCliente.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validaCampos() {
        String nome = etNome.getText().toString().trim();
        String cpf = etCpf.getText().toString().trim();
        String dtNasc = etDtNasc.getText().toString().trim();

        if (nome.isEmpty() || cpf.isEmpty() || dtNasc.isEmpty()) {
            return false;
        }

        return true;
    }
    private void limpaCampos() {
        etCod.setText("0");
        etNome.setText("");
        etCpf.setText("");
        etDtNasc.setText("");

    }
}