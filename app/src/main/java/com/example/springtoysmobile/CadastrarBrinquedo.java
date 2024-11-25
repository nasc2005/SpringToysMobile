package com.example.springtoysmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.springtoysmobile.Controller.BrinquedoController;
import com.example.springtoysmobile.model.Brinquedo;

public class CadastrarBrinquedo extends AppCompatActivity {

    private EditText edtCodigo, edtNome, edtDescricao, edtValor, edtImagem, edtDetalhes, edtMarca, edtDestaque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_brinquedo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtCodigo = findViewById(R.id.edtCodigo);
        edtNome = findViewById(R.id.edtNome);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtImagem = findViewById(R.id.edtImagem);
        edtDetalhes = findViewById(R.id.edtDetalhes);
        edtMarca = findViewById(R.id.edtMarca);
        edtDestaque = findViewById(R.id.edtDestaque);

    }

    public void CadastrarDados(View view) {
        String nome = edtNome.getText().toString();
        String email = edtDescricao.getText().toString();
        String senha = edtValor.getText().toString();

        if (email.trim().isEmpty()) {
            // O EditText está vazio
            Toast.makeText(this, "O campo Email não pode estar vazio", Toast.LENGTH_SHORT).show();
            return;
        } else if (senha.trim().isEmpty()) {
            Toast.makeText(this, "O campo Senha não pode estar vazio", Toast.LENGTH_SHORT).show();
            return;
        } else if (nome.trim().isEmpty()) {
            Toast.makeText(this, "O campo Nome não pode estar vazio", Toast.LENGTH_SHORT).show();
            return;
        }

        Brinquedo b = new Brinquedo();
        b.setCodigo(edtCodigo.getText().toString());
        b.setNome(edtNome.getText().toString());
        b.setDescricao(edtDescricao.getText().toString());
        b.setValor(Double.parseDouble(edtValor.getText().toString())); // Supondo que o valor seja um número decimal
        b.setImagem(edtImagem.getText().toString());
        b.setDetalhes(edtDetalhes.getText().toString());
        b.setMarca(edtMarca.getText().toString());
        b.setDestaque(edtDestaque.getText().toString());

        BrinquedoController bcont= new BrinquedoController();
        int id =  bcont.insert(b);
        Toast.makeText(getApplicationContext(), "Brinquedo inserido com sucesso! ID: " + id, Toast.LENGTH_LONG).show();
        finish();
    }

}