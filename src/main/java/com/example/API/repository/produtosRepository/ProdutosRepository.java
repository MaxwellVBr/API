package com.example.API.repository.produtosRepository;

import com.example.API.model.produtos.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    List<Produtos> findByNomeContainingIgnoreCase(String nome);

}
