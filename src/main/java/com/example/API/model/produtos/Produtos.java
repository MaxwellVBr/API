package com.example.API.model.produtos;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "Produtos")
@EqualsAndHashCode(of = "id")
public class Produtos {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;
    private String nome;
    private String descricao;
    private Integer preco;

    public Produtos(Long idProduto, String nome, String descricao, Integer preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produtos(ProdutosRequestDTO produtosData) {
        this.nome = produtosData.nome();
        this.descricao = produtosData.descricao();
        this.preco = produtosData.preco();
    }

    public Produtos() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }
}
