package com.example.springtoysmobile.Controller;

import android.util.Log;

import com.example.springtoysmobile.Service.FindAllRequest;
import com.example.springtoysmobile.model.Brinquedo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BrinquedoController {

    public List<Brinquedo> findAll() {
        List<Brinquedo> brinquedos = new ArrayList<>();
        Log.i("JSON_PROCESSING", "Iniciando processamento do JSON...");

        try {
            // Faz a requisição e obtém o JSON como string
            FindAllRequest findAllRequest = new FindAllRequest();
            String jsonString = findAllRequest.execute().get();
            Log.i("JSON_RECEIVED", jsonString);

            // Usa o ObjectMapper para converter o JSON diretamente em uma lista de objetos Brinquedo
            ObjectMapper objectMapper = new ObjectMapper();
            brinquedos = objectMapper.readValue(jsonString,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Brinquedo.class));

            // Log dos brinquedos para verificar se a conversão foi bem-sucedida
            Log.i("BRINQUEDOS_PROCESSADOS", brinquedos.toString());

        } catch (ExecutionException | InterruptedException e) {
            Log.e("JSON_ERROR", "Erro ao executar a requisição: ", e);
        } catch (JsonProcessingException e) {
            Log.e("JSON_ERROR", "Erro ao processar o JSON: ", e);
        }

        return brinquedos;
    }

}
