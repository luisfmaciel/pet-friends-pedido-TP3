package br.edu.infnet.pedidopetfriends.commands;

public class AdicionarProdutoCommand extends BaseCommand<String> {
    public final String produtoId;
    public final double valor;

    public AdicionarProdutoCommand(String id, String produtoId, double valor) {
        super(id);
        this.produtoId = produtoId;
        this.valor = valor;
    }
}