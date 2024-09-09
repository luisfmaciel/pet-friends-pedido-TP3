package br.edu.infnet.pedidopetfriends.events;

import br.edu.infnet.pedidopetfriends.domain.EstadoPedido;

public class PedidoProcessado extends BaseEvent<String> {
    public final EstadoPedido
            estado;

    public PedidoProcessado(String id, EstadoPedido estado) {
        super(id);
        this.estado = estado;
    }
}