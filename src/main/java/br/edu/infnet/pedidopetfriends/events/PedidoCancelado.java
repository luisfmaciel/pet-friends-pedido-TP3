package br.edu.infnet.pedidopetfriends.events;

public class PedidoCancelado extends BaseEvent<String> {
    public PedidoCancelado(String id) {
        super(id);
    }
}