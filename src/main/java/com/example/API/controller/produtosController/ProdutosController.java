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
    public ResponseEntity<List<ProdutosResponseDTO>> getAll(){
        List<ProdutosResponseDTO> listProdutos = repository.findAll().stream().map(ProdutosResponseDTO::new).toList();

        if (listProdutos.isEmpty()){
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(listProdutos); // 200 OK com a lista
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutosResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<Produtos> produtos = repository.findByNomeContainingIgnoreCase(nome);
        List<ProdutosResponseDTO> listProdutos = produtos.stream().map(ProdutosResponseDTO::new).toList();

        if (listProdutos.isEmpty()){
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(listProdutos); // 200 OK com a lista
    }



    @PostMapping
    public ResponseEntity<Void> salvarProdutos(@RequestBody ProdutosRequestDTO produtosData){
        Produtos produtos = new Produtos(produtosData);
        repository.save(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarProdutos(@PathVariable Long id, @RequestBody ProdutosRequestDTO produtosData){
        Produtos produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtosData.atualizarEntidade(produto);
        repository.save(produto);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemResponse> DeletarProdutos(@PathVariable Long id){
        if (!repository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404
                    .body(new MensagemResponse("Produto não encontrado"));
        }

        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK) // 200 sem body
                .build();
    }

}
