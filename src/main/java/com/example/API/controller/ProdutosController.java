package com.example.API.controller;

import com.example.API.model.Produtos;
import com.example.API.model.ProdutosRequestDTO;
import com.example.API.repository.ProdutosRepository;
import com.example.API.model.ProdutosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repository;

    @GetMapping
    public List<ProdutosResponseDTO> getAll(){
        List<ProdutosResponseDTO> listProdutos = repository.findAll().stream().map(ProdutosResponseDTO::new).toList();
        return listProdutos;
    }
    @GetMapping("/buscar")
    public List<ProdutosResponseDTO> buscarPorNome(@RequestParam String nome) {
        List<Produtos> produtos = repository.findByNomeContainingIgnoreCase(nome);
        return produtos.stream().map(ProdutosResponseDTO::new).toList();
    }

    @PostMapping
    public ResponseEntity<Void> salvarProdutos(@RequestBody ProdutosRequestDTO produtosData){
        Produtos produtos = new Produtos(produtosData);
        repository.save(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public void alterarProdutos(@PathVariable Long id, @RequestBody ProdutosRequestDTO produtosData){
        Produtos produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtosData.atualizarEntidade(produto);
        repository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void DeletarProdutos(@PathVariable Long id){
        repository.deleteById(id);
    }


}
