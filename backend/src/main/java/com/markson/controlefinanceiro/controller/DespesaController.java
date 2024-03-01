package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosAtualizacaoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoCategoria;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.DespesaService;
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

import java.util.List;

@RestController
@RequestMapping("/despesas")
@Tag(
        name = "Despesas CRUD API",
        description = "Operações básicas possíveis com despesas"
)
public class DespesaController {
    private final DespesaService despesaService;

    @Autowired
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @Operation(
            summary = "Cadastra uma despesa",
            description = "Cadastra uma despesa"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Despesa criada com sucesso"
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
    public ResponseEntity<DadosDetalhamentoDespesa> cadastrar(
            @Valid
            @RequestBody
            DadosCadastramentoDespesa dadosCadastramento,
            UriComponentsBuilder uriBuilder
    ) {
        DadosDetalhamentoDespesa dadosDetalhamento = despesaService.cadastrar(dadosCadastramento);

        var uri = uriBuilder.path("/despesas/{id}")
                .buildAndExpand(dadosDetalhamento.id())
                .toUri();
        return ResponseEntity.created(uri)
                .body(dadosDetalhamento);
    }

    @Operation(
            summary = "Recupera uma despesa com base no id ",
            description = "Recupera uma despesa com base no id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Detalhamento da despesa com base no id"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Despesa com id informado não foi encontrada no sistema",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoDespesa> detalhar(@PathVariable Long id) {
        var despesa = despesaService.detalhar(id);
        return ResponseEntity.ok(despesa);
    }

    @Operation(
            summary = "Recupera todas as despesas cadastradas no sistema",
            description = "Recupera todas as despesas cadastradas no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Retorna um Page com despesas cadastradas no sistema"
    )
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoDespesa>> listar(
            @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC)
            Pageable paginacao,
            @RequestParam(value = "descricao", required = false, defaultValue = "")
            String descricao
    ) {
        var paginas = despesaService.listar(paginacao, descricao);

        return ResponseEntity.ok(paginas);
    }

    @Operation(
            summary = "Recupera todas as categorias de despesas cadastradas no sistema",
            description = "Recupera todas as categorias de despesas cadastradas no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Retorna uma lista com categorias cadastradas no sistema"
    )
    @GetMapping("/categorias")
    public ResponseEntity<List<DadosDetalhamentoCategoria>> listarCategorias() {
        return ResponseEntity.ok(despesaService.listarCategorias());
    }

    @Operation(
            summary = "Altera uma despesa com base no id",
            description = "Altera uma despesa com base no id "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Despesa atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Despesa com id informado não foi encontrada no sistema",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDespesa> atualizar(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            DadosAtualizacaoDespesa dadosAtualizacao
    ) {
        DadosDetalhamentoDespesa dadosDetalhamento = despesaService.atualizar(id, dadosAtualizacao);

        return ResponseEntity.ok(dadosDetalhamento);
    }

    @Operation(
            summary = "Deleta uma despesa com base no id",
            description = "Deleta uma despesa com base no id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Despesa deletada com sucesso",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Despesa com id informado não foi encontrada no sistema",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    )
            )
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        despesaService.deletar(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @Operation(
            summary = "Recupera as despesas do mês do ano informado",
            description = "Recupera as despesas do mês do ano informado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de despesas do mês do ano informado"
    )
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<Page<DadosDetalhamentoDespesa>> listarReceitaDoMes(@PathVariable int ano, @PathVariable int mes, @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<DadosDetalhamentoDespesa> despesas = despesaService.listarReceitaDoMes(ano, mes, paginacao);

        return ResponseEntity.ok(despesas);
    }
}