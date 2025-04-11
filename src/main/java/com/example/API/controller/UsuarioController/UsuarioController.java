package com.example.API.controller.UsuarioController;

import com.example.API.model.User.Usuario;
import com.example.API.model.User.UsuarioRequestDTO;
import com.example.API.repository.usuarioRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("login")
public class UsuarioController {

    public record MensagemResponse(String mensagem) {}

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return ResponseEntity.ok(usuario); // 200 OK com a lista
    }

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody UsuarioRequestDTO usuarioData){
        Usuario usuario = new Usuario(usuarioData);
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioData){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuarioData.atualizarEntidade(usuario);
        repository.save(usuario);
        return ResponseEntity.noContent().build() ; // 200 OK Sem Body
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioController.MensagemResponse> DeletarProdutos(@PathVariable Long id){
        if (!repository.existsById(id)){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND) // 404
                    .body(new MensagemResponse("Produto não encontrado"));
        }
        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK sem Body
                .build();
    }

}
