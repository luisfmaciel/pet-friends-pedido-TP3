package br.edu.infnet.pedidopetfriends.events;

import java.util.List;

public class PedidoCriado extends BaseEvent<String> {
    public final String clienteId;
    public final List<String> produtos;
    public final double total;

    public PedidoCriado(String id, String clienteId, List<String> produtos, double total) {
        super(id);
        this.clienteId = clienteId;
        this.produtos = produtos;
        this.total = total;
    }
}