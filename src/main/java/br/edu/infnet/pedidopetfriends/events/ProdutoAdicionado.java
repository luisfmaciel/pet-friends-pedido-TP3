package br.edu.infnet.pedidopetfriends.events;

public class ProdutoAdicionado extends BaseEvent<String> {
    public final String produtoId;
    public final double valor;

    public ProdutoAdicionado(String id, String produtoId, double valor) {
        super(id);
        this.produtoId = produtoId;
        this.valor = valor;
    }
}