package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.autenticacao.DadosAutenticacao;
import com.markson.controlefinanceiro.domain.usuario.Usuario;
import com.markson.controlefinanceiro.infra.security.token.DadosTokenJWT;
import com.markson.controlefinanceiro.infra.security.token.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(
        name = "Autenticação",
        description = "Endpoint para logar no sistema"
)
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(
            summary = "Loga no sistema",
            description = "Loga no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Logado com sucesso, reposta será o JWT token utilizado em outras requisições"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Usuário ou senha inválidos",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @PostMapping
    public ResponseEntity logar(@Valid @RequestBody DadosAutenticacao usuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuario.login(), usuario.senha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwtToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(
                new DadosTokenJWT(jwtToken)
        );
    }
}
