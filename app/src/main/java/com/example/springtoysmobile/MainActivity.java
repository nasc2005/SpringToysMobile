package com.example.springtoysmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.springtoysmobile.Controller.BrinquedoController;
import com.example.springtoysmobile.model.Brinquedo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listViewAbertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializando o ListView
        listViewAbertas = findViewById(R.id.listabertas);

        carregarMaratonasAbertas();

        /* Adicionando o OnItemClickListener */
        listViewAbertas.setOnItemClickListener((parent, view, position, id) -> {
            Brinquedo brinquedoSelecionado = (Brinquedo) parent.getItemAtPosition(position);
            Toast.makeText(this, String.valueOf(brinquedoSelecionado.getCodBrinquedo()), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, EditarBrinquedo.class);
            intent.putExtra("id", brinquedoSelecionado.getCodBrinquedo());


            startActivityForResult(intent, 1);
        });
    }




    private void carregarMaratonasAbertas() {
        BrinquedoController bcont= new BrinquedoController();
        List<Brinquedo> listaBrinquedo = bcont.findAll();

        // Verificar se a lista está preenchida
        if (listaBrinquedo == null || listaBrinquedo.isEmpty()) {
            Toast.makeText(this, "Nenhum Brinquedo encontrado", Toast.LENGTH_SHORT).show();
            Log.d("VisualizarAbertas", "Nenhum Brinquedo encontrado");
        }

        // Criando um ArrayAdapter para mostrar a lista
        assert listaBrinquedo != null;
        ArrayAdapter<Brinquedo> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaBrinquedo
        );

        listViewAbertas.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            carregarMaratonasAbertas();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            carregarMaratonasAbertas();
        }
    }

    public void cadastrar(View view){
        Intent intent = new Intent(MainActivity.this, CadastrarBrinquedo.class);


        startActivityForResult(intent, 1);
    }

}