package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forcavendasapp.R;

public class MainActivity extends AppCompatActivity {

    private Button btCadEndereco;
    private Button btCadCliente;
    private Button btCadItem;
    private Button btPedVenda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadEndereco = findViewById(R.id.btCadEndereco);
        btCadCliente = findViewById(R.id.btCadCliente);
        btCadItem = findViewById(R.id.btCadItem);
        btPedVenda = findViewById(R.id.btPedVenda);

        btCadEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroEndereco.class);
                startActivity(intent);
            }
        });
        btCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroCliente.class);
                startActivity(intent);
            }
        });
        btCadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroItem.class);
                startActivity(intent);
            }
        });
        btPedVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PedidoVenda.class);
                startActivity(intent);
            }
        });
    }
}