package com.example.springtoysmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class EditarBrinquedo extends AppCompatActivity {
    private int brinquedoCod;
    private EditText edtCodBrinquedo, edtCodigo, edtNome, edtDescricao, edtValor, edtImagem, edtDetalhes, edtMarca, edtDestaque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_brinquedo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        brinquedoCod = intent.getIntExtra("id", -1);
        edtCodBrinquedo = findViewById(R.id.edtCodBrinquedo);
        edtCodigo = findViewById(R.id.edtCodigo);
        edtNome = findViewById(R.id.edtNome);
        edtDescricao = findViewById(R.id.edtDescricao);
        edtValor = findViewById(R.id.edtValor);
        edtImagem = findViewById(R.id.edtImagem);
        edtDetalhes = findViewById(R.id.edtDetalhes);
        edtMarca = findViewById(R.id.edtMarca);
        edtDestaque = findViewById(R.id.edtDestaque);

        BrinquedoController bcont = new BrinquedoController();
        Brinquedo brinquedo = bcont.findById(String.valueOf(brinquedoCod));
        // Verificar se o objeto Corredores foi carregado corretamente
        if (brinquedo == null) {
            Log.e("EditarUsuario", "brinquedo não encontrado para userId: " + brinquedoCod);
            Toast.makeText(this, "brinquedo não encontrado.", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Carregar dados nos campos de entrada
            Log.d("EditarUsuario", "brinquedo encontrado: " + brinquedo.getNome());
            edtCodBrinquedo.setText(String.valueOf(brinquedo.getCodBrinquedo()));
            edtCodigo.setText(brinquedo.getCodigo());
            edtNome.setText(brinquedo.getNome());
            edtDescricao.setText(brinquedo.getDescricao());
            edtValor.setText(String.valueOf(brinquedo.getValor()));
            edtImagem.setText(brinquedo.getImagem());
            edtDetalhes.setText(brinquedo.getDetalhes());
            edtMarca.setText(brinquedo.getMarca());
            edtDestaque.setText(brinquedo.getDestaque());

        }
    }
    public void AtualizarMaratona(View view) {


        String nome = edtNome.getText().toString();
        String descricao = edtDescricao.getText().toString();

        // Verificação de campos obrigatórios
        if (nome.trim().isEmpty() || descricao.trim().isEmpty()) {
            Toast.makeText(this, "Campo nome e descricao precisam ser preenchidos", Toast.LENGTH_SHORT).show();
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

        try {
        BrinquedoController bcont = new BrinquedoController();
        bcont.update(String.valueOf(edtCodBrinquedo.getText()), b);

            Toast.makeText(getApplicationContext(), "Brinquedo Atualizado com sucesso " , Toast.LENGTH_LONG).show();



            setResult(RESULT_OK);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Erro ao Atualizar Brinquedo: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
    public void ExcluirBrinquedo(View view) {
        try {
            BrinquedoController bcont = new BrinquedoController();
            bcont.deleteById(String.valueOf(edtCodBrinquedo.getText()));

            Toast.makeText(getApplicationContext(), "Brinquedo Deletado com sucesso " , Toast.LENGTH_LONG).show();



            setResult(RESULT_OK);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Erro ao Deletar Brinquedo: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
    }