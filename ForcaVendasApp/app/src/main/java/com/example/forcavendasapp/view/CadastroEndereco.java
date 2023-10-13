package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.EnderecoController;
import com.example.forcavendasapp.model.Endereco;

public class CadastroEndereco extends AppCompatActivity {

    private EditText etLog;
    private EditText etNum;
    private EditText etBai;
    private EditText etCid;
    private EditText etUF;
    private EditText etCod;
    private Button btCancel;
    private Button btCadastrar;
    private EnderecoController ec = new EnderecoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);

        etBai = findViewById(R.id.etBai);
        etUF = findViewById(R.id.etUf);
        etCid = findViewById(R.id.etCid);
        etLog = findViewById(R.id.etLog);
        etNum = findViewById(R.id.etNum);
        etCod = findViewById(R.id.etCod);
        btCadastrar = findViewById(R.id.btCadastrar);
        btCancel = findViewById(R.id.btCancel);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroEndereco.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCod == null || etCod.getText().toString() == "") {
                    etCod.setText("0");
                }
                if (etCod != null && !etCod.getText().toString().isEmpty() && !etCod.getText().toString().equals("0")) {
                    if (validaCampos()) {
                        Endereco end = new Endereco();
                        end.setCodigo(Integer.parseInt(etCod.getText().toString()));
                        end.setBairro(etBai.getText().toString());
                        end.setCidade(etCid.getText().toString());
                        end.setLogradouro(etLog.getText().toString());
                        end.setNumero(Integer.parseInt(etNum.getText().toString()));
                        end.setUf(etUF.getText().toString());
                        ec.atualizarEndereco(end);
                        limpaCampos();
                        Toast.makeText(CadastroEndereco.this, "Endereço atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Pelo menos um campo está vazio, exiba uma mensagem de erro ao usuário
                        Toast.makeText(CadastroEndereco.this, "Por favor, preencha todos os campos para atualizar um endereço", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (validaCampos()) {
                        Endereco end = new Endereco();
                        end.setBairro(etBai.getText().toString());
                        end.setCidade(etCid.getText().toString());
                        end.setLogradouro(etLog.getText().toString());
                        end.setNumero(Integer.parseInt(etNum.getText().toString()));
                        end.setUf(etUF.getText().toString());
                        ec.salvarEndereco(end);
                        limpaCampos();
                        Toast.makeText(CadastroEndereco.this, "Endereço Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CadastroEndereco.this, "Por favor, preencha todos os campos!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean validaCampos() {

        String logradouro = etLog.getText().toString().trim();
        String numero = etNum.getText().toString().trim();
        String bairro = etBai.getText().toString().trim();
        String cidade = etCid.getText().toString().trim();
        String uf = etUF.getText().toString().trim();

        if (logradouro.isEmpty() || numero.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty()) {
            return false;
        }

        return true;
    }

    private void limpaCampos() {
        etCod.setText("0");
        etBai.setText("");
        etLog.setText("");
        etNum.setText("");
        etCid.setText("");
        etUF.setText("");
    }
}