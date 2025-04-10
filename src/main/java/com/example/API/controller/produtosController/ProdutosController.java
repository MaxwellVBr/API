package com.example.API.controller.produtosController;

import com.example.API.model.produtos.Produtos;
import com.example.API.model.produtos.ProdutosRequestDTO;
import com.example.API.repository.produtosRepository.ProdutosRepository;
import com.example.API.model.produtos.ProdutosResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("produtos")
public class ProdutosController {

    public record MensagemResponse(String mensagem) {}

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
    public void salvarProdutos(@RequestBody ProdutosRequestDTO produtosData){
        Produtos produtos = new Produtos(produtosData);
        repository.save(produtos);
        return;
    }

    @PutMapping("/{id}")
    public void alterarProdutos(@PathVariable Long id, @RequestBody ProdutosRequestDTO produtosData){
        Produtos produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtosData.atualizarEntidade(produto);
        repository.save(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemResponse> DeletarProdutos(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MensagemResponse("Produto deletado com sucesso"));
    }


}
