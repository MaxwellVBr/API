package com.example.API.model.produtos;

public record ProdutosRequestDTO(String nome, String descricao, Integer preco) {
    public void atualizarEntidade(Produtos produto) {
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
    }
}
