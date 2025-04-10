package com.example.API.model.produtos;

public record ProdutosResponseDTO(Long idProduto, String nome, String descricao, Integer preco ) {
    public ProdutosResponseDTO(Produtos produtos){
        this(produtos.getIdProduto(), produtos.getNome(), produtos.getDescricao(), produtos.getPreco());
    }

}
