package br.edu.infnet.pedidopetfriends.infra;

import br.edu.infnet.pedidopetfriends.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, String> {
}