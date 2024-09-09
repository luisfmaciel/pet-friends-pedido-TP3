package br.edu.infnet.pedidopetfriends.domain;

import br.edu.infnet.pedidopetfriends.commands.AdicionarProdutoCommand;
import br.edu.infnet.pedidopetfriends.commands.CancelarPedidoCommand;
import br.edu.infnet.pedidopetfriends.commands.CriarPedidoCommand;
import br.edu.infnet.pedidopetfriends.events.PedidoCancelado;
import br.edu.infnet.pedidopetfriends.events.PedidoCriado;
import br.edu.infnet.pedidopetfriends.events.PedidoProcessado;
import br.edu.infnet.pedidopetfriends.events.ProdutoAdicionado;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Data
@Aggregate
@Entity
public class Pedido {

    @AggregateIdentifier
    @Id
    private String id;
    private String clienteId;
    private List<String> produtos = new ArrayList<>();
    private double total;
    private String estado;

    public Pedido() {}

    @CommandHandler
    public Pedido(CriarPedidoCommand comando) {
        AggregateLifecycle.apply(new PedidoCriado(comando.id, comando.clienteId, comando.produtos, comando.total));
    }

    @EventSourcingHandler
    protected void on(PedidoCriado evento) {
        this.id = evento.id;
        this.clienteId = evento.clienteId;
        this.produtos = evento.produtos;
        this.total = evento.total;
        this.estado = String.valueOf(EstadoPedido.CRIADO);

        AggregateLifecycle.apply(new PedidoProcessado(this.id, EstadoPedido.PROCESSANDO));
    }

    @EventSourcingHandler
    protected void on(PedidoProcessado evento) {
        this.estado = String.valueOf(evento.estado);
    }

    @CommandHandler
    protected void on(AdicionarProdutoCommand comando) {
        AggregateLifecycle.apply(new ProdutoAdicionado(comando.id, comando.produtoId, comando.valor));
    }
    @EventSourcingHandler
    protected void on(ProdutoAdicionado evento) {
        this.produtos.add(evento.produtoId);
        this.total += evento.valor;
    }

    @CommandHandler
    protected void on(CancelarPedidoCommand comando) {
        AggregateLifecycle.apply(new PedidoCancelado(comando.id));
    }
    @EventSourcingHandler
    protected void on(PedidoCancelado evento) {
        this.estado = String.valueOf(EstadoPedido.CANCELADO);
    }
}
