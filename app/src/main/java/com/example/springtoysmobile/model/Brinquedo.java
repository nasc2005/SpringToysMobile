package com.example.springtoysmobile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brinquedo {

    private int codBrinquedo;
    private String codigo;
    private String nome;
    private String descricao;
    private Double valor;
    private String imagem;
    private String detalhes;
    private String marca;
    private String destaque;

    @Override
    public String toString() {
        return nome; // Retorna o nome do brinquedo para exibição na lista
    }
    public Brinquedo() {
    }

    public Brinquedo(int codBrinquedo, String codigo, String descricao, String nome, Double valor, String imagem, String detalhes, String marca, String destaque) {
        super();
        this.codBrinquedo = codBrinquedo;
        this.codigo = codigo;
        this.descricao = descricao;
        this.nome = nome;
        this.valor = valor;
        this.imagem = imagem;
        this.detalhes = detalhes;
        this.marca = marca;
        this.destaque = destaque;
    }

    public Brinquedo(String nome) {
        this.nome = nome;
    }

    public int getCodBrinquedo() {
        return codBrinquedo;
    }

    public void setCodBrinquedo(int codBrinquedo) {
        this.codBrinquedo = codBrinquedo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDestaque() {
        return destaque;
    }

    public void setDestaque(String destaque) {
        this.destaque = destaque;
    }
}
