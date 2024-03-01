package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.receita.dto.DadosAtualizacaoReceita;
import com.markson.controlefinanceiro.domain.receita.dto.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.dto.DadosDetalhamentoReceita;
import com.markson.controlefinanceiro.domain.receita.ReceitaService;
import com.markson.controlefinanceiro.infra.exception.TratadorDeErros;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/receitas")
@Tag(
        name = "Receitas CRUD API",
        description = "Operações básicas possíveis com receitas"
)
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;

    @Operation(
            summary = "Cadastra uma receita",
            description = "Cadastra uma receita"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Receita criada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Body mal formado",
                    content = @Content(
                            schema = @Schema(implementation = TratadorDeErros.DadosErroValidacao.class)
                    )
            )
    })
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoReceita> cadastrar(@Valid @RequestBody DadosCadastramentoReceita dadosCadastramento,
                                                              UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoReceita dadosDetalhamento = receitaService.cadastrar(dadosCadastramento);

        var uri = uriBuilder.path("/receitas/{id}")
                .buildAndExpand(dadosDetalhamento.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(dadosDetalhamento);
    }

    @Operation(
            summary = "Recupera todas as receitas cadastradas no sistema",
            description = "Recupera todas as receitas cadastradas no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Retorna um Page com receitas cadastradas no sistema"
    )
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoReceita>> listar(
            @PageableDefault(
                    size = 20,
                    sort = "data",
                    direction = Sort.Direction.DESC)
            Pageable paginacao,
            @RequestParam(
                    required = false,
                    value = "descricao",
                    defaultValue = "")
            String descricao
    ) {
        Page<DadosDetalhamentoReceita> dadosDetalhamento = receitaService.listar(paginacao, descricao);
        return ResponseEntity.ok(dadosDetalhamento);
    }

    @Operation(
            summary = "Recupera uma receita com base no id ",
            description = "Recupera uma receita com base no id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalhamento da receita com base no id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Receita com id informado não foi encontrada no sistema",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoReceita> detalhar(
            @PathVariable
            Long id
    ) {
        DadosDetalhamentoReceita dadosDetalhamento = receitaService.detalhar(id);

        return ResponseEntity.ok(dadosDetalhamento);
    }

    @Operation(
            summary = "Altera uma receita com base no id",
            description = "Altera uma receita com base no id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Receita atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Receita com id informado não foi encontrada no sistema",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoReceita> atualizar(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            DadosAtualizacaoReceita dadosAtualizacao
    ) {
        DadosDetalhamentoReceita dadosDetalhamento = receitaService.atualizar(id, dadosAtualizacao);
        return ResponseEntity.ok(dadosDetalhamento);
    }

    @Operation(
            summary = "Deleta uma receita com base no id",
            description = "Deleta uma receita com base no id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Receita deletada com sucesso",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Receita com id informado não foi encontrada no sistema",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Recupera as receitas do mês do ano informado",
            description = "Recupera as receitas do mês do ano informado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de receitas do mês do ano informado"
    )
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<Page<DadosDetalhamentoReceita>> listarReceitaDoMes( @PathVariable int ano, @PathVariable int mes, @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<DadosDetalhamentoReceita> receitas = receitaService.listarReceitasDoMes(ano, mes, paginacao);

        return ResponseEntity.ok(receitas);
    }
}
