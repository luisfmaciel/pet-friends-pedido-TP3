package br.edu.infnet.pedidopetfriends.services;

import br.edu.infnet.pedidopetfriends.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoQueryService {
    Optional<Pedido> obterPorId(String id);
    List<Object> listarEventos(String id);
}