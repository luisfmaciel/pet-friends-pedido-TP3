package br.edu.infnet.pedidopetfriends.controllers;

import br.edu.infnet.pedidopetfriends.domain.Pedido;
import br.edu.infnet.pedidopetfriends.services.impl.PedidoCommandServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoCommandController {

    private final PedidoCommandServiceImpl service;

    public PedidoCommandController(PedidoCommandServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pedido", description = "Este endpoint cria um novo pedido com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para a criação do pedido")
    })
    public CompletableFuture<String> criarPedido(@RequestBody Pedido pedido) {
        return service.criarPedido(pedido);
    }

    @PutMapping(value = "/adicionar-produto")
    @Operation(
            summary = "Adiciona um produto ao pedido",
            description = "Este endpoint adiciona um produto a um pedido existente com base no ID do pedido e os dados do produto fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado ao pedido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos para adicionar o produto ao pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public CompletableFuture<String> adicionarProduto(@RequestBody Map<String, Object> produto) {
        return service.adicionarProduto((String) produto.get("id"), (String) produto.get("produtoId"), (Double) produto.get("valor"));
    }

    @PutMapping(value = "/cancelar")
    @Operation(summary = "Cancela um pedido", description = "Este endpoint cancela um pedido existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public CompletableFuture<String> cancelarPedido(@RequestBody String id) {
        return service.cancelarPedido(id);
    }
}
