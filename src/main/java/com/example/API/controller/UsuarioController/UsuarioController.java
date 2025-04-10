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
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public void salvarUsuario(@RequestBody UsuarioRequestDTO usuarioData){
        Usuario usuario = new Usuario(usuarioData);
        repository.save(usuario);
        return;
    }

    @PutMapping("/{id}")
    public void alterarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioData){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuarioData.atualizarEntidade(usuario);
        repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioController.MensagemResponse> DeletarProdutos(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MensagemResponse("Produto deletado com sucesso"));
    }

}
