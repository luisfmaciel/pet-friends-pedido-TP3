package br.edu.infnet.pedidopetfriends.commands;

import java.util.List;

public class CriarPedidoCommand extends BaseCommand<String> {
    public final String clienteId;
    public final List<String> produtos;
    public final double total;

    public CriarPedidoCommand(String id, String clienteId, List<String> produtos, double total) {
        super(id);
        this.clienteId = clienteId;
        this.produtos = produtos;
        this.total = total;
    }
}
