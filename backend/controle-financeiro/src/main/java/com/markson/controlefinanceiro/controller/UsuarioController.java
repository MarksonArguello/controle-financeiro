package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.autenticacao.AutenticacaoService;
import com.markson.controlefinanceiro.domain.autenticacao.DadosAutenticacao;
import com.markson.controlefinanceiro.domain.usuario.DadosDetalhamentoUsuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@Tag(
        name = "Cadastro de Usuários",
        description = "Endopoint para cadastro de usuários no sistema"
)
public class UsuarioController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Operation(
            summary = "Cadastra um usuário no sistema",
            description = "Cadastra um usuário no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Login já está sendo utilizado",
                    content = @Content(
                            schema = @Schema(example = "Login já está sendo utilizado")
                    )
            )
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosAutenticacao dadosAutenticacao, UriComponentsBuilder uriBuilder) {
        var usuario = autenticacaoService.cadastrar(dadosAutenticacao);
        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }
}
