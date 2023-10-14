package com.example.forcavendasapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.forcavendasapp.R;
import com.example.forcavendasapp.controller.ItemController;
import com.example.forcavendasapp.model.Item;

public class CadastroItem extends AppCompatActivity {

    private EditText etCod;
    private EditText etDesc;
    private EditText etVlrUnit;
    private EditText etUniMed;
    private Button btCancel;
    private Button btCadastrar;
    private ItemController itemController = new ItemController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        etCod =findViewById(R.id.etCod);
        etDesc = findViewById(R.id.etDesc);
        etUniMed = findViewById(R.id.etUniMed);
        etVlrUnit = findViewById(R.id.etVlrUnit);
        btCadastrar = findViewById(R.id.btCadastrar);
        btCancel = findViewById(R.id.btCancel);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroItem.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    Item item = new Item();
                    item.setDescricao(etDesc.getText().toString());
                    item.setVlrUnit(Integer.parseInt(etVlrUnit.getText().toString()));
                    item.setUnMedida(etUniMed.getText().toString());

                    itemController.salvarItem(item);
                    limpaCampos();
                    Toast.makeText(CadastroItem.this, "Item salvo com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CadastroItem.this, "Por favor, preencha todos os campos para criar um novo item", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(CadastroItem.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean validaCampos() {
        String descricao = etDesc.getText().toString().trim();
        String vlrUnit = etVlrUnit.getText().toString().trim();
        String uniMed = etUniMed.getText().toString().trim();

        return !descricao.isEmpty() && !vlrUnit.isEmpty() && !uniMed.isEmpty();
    }

    private void limpaCampos() {
        etCod.setText("0");
        etDesc.setText("");
        etVlrUnit.setText("");
        etUniMed.setText("");
    }
}