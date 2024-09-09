package br.edu.infnet.pedidopetfriends.controllers;

import br.edu.infnet.pedidopetfriends.domain.Pedido;
import br.edu.infnet.pedidopetfriends.services.impl.PedidoQueryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoQueryController {

    private final PedidoQueryServiceImpl service;

    public PedidoQueryController(PedidoQueryServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/{id}/eventos")
    @Operation(summary = "Lista todos os eventos de um pedido", description = "Este endpoint retorna todos os eventos associados a um pedido específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public List<Object> listarEventos(@PathVariable(value = "id") String id) {
        return service.listarEventos(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um pedido por ID", description = "Este endpoint retorna um pedido específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public Pedido obterPorId(@PathVariable(value = "id") String id) {
        return service.obterPorId(id).get();
    }
}
