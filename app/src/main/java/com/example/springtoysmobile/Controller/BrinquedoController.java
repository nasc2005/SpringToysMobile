package com.example.springtoysmobile.Controller;

import android.util.Log;

import com.example.springtoysmobile.Service.DeleteRequest;
import com.example.springtoysmobile.Service.FindAllRequest;
import com.example.springtoysmobile.Service.FindByIdRequest;
import com.example.springtoysmobile.Service.InsertRequest;
import com.example.springtoysmobile.Service.UpdateRequest;
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

    public Brinquedo findById(String id) {
        Brinquedo brinquedo = null;

        try {
            // Faz a solicitação para obter o JSON correspondente ao ID
            FindByIdRequest findByIdRequest = new FindByIdRequest();
            String jsonString = findByIdRequest.execute(id).get();

            // Desserializa o JSON diretamente para um objeto Brinquedo
            ObjectMapper objectMapper = new ObjectMapper();
            brinquedo = objectMapper.readValue(jsonString, Brinquedo.class);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return brinquedo;
    }
    public int insert(Brinquedo brinquedo){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonUser = objectMapper.writeValueAsString(brinquedo);

            InsertRequest insertRequest = new InsertRequest();
            String jsonString = insertRequest.execute(jsonUser).get();

            Map<String, Object> map = objectMapper.readValue(jsonString, Map.class);
            brinquedo = objectMapper.readValue(jsonString, Brinquedo.class);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return brinquedo.getCodBrinquedo();
    }
    public void update(String id, Brinquedo brinquedo){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonUser = objectMapper.writeValueAsString(brinquedo);

            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.execute(id, jsonUser).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(String id){
        try {
            DeleteRequest deleteRequest = new DeleteRequest();
            deleteRequest.execute(id).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
