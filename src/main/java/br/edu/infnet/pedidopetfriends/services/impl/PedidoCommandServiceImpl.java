package br.edu.infnet.pedidopetfriends.services.impl;

import br.edu.infnet.pedidopetfriends.commands.AdicionarProdutoCommand;
import br.edu.infnet.pedidopetfriends.commands.CancelarPedidoCommand;
import br.edu.infnet.pedidopetfriends.commands.CriarPedidoCommand;
import br.edu.infnet.pedidopetfriends.domain.Pedido;
import br.edu.infnet.pedidopetfriends.services.PedidoCommandService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PedidoCommandServiceImpl implements PedidoCommandService {

    @Autowired
    private final CommandGateway commandGateway;

    public PedidoCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> criarPedido(Pedido pedido) {
        return commandGateway.send(new CriarPedidoCommand(
                UUID.randomUUID().toString(),
                pedido.getClienteId(),
                pedido.getProdutos(),
                pedido.getTotal()));
    }

    @Override
    public CompletableFuture<String> adicionarProduto(String id, String produtoId, double valor) {
        return commandGateway.send(new AdicionarProdutoCommand(id, produtoId, valor));
    }

    @Override
    public CompletableFuture<String> cancelarPedido(String id) {
        return commandGateway.send(new CancelarPedidoCommand(id));
    }
}
