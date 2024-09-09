package br.edu.infnet.pedidopetfriends.services.impl;

import br.edu.infnet.pedidopetfriends.domain.Pedido;
import br.edu.infnet.pedidopetfriends.infra.PedidoRepository;
import br.edu.infnet.pedidopetfriends.services.PedidoQueryService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoQueryServiceImpl implements PedidoQueryService {

    private final EventStore eventStore;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoQueryServiceImpl(EventStore eventStore, PedidoRepository pedidoRepository) {
        this.eventStore = eventStore;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Object> listarEventos(String id) {
        return eventStore.readEvents(id, 0)
                .asStream()
                .map(Message::getPayload).collect(Collectors.toList());
    }

    public Optional<Pedido> obterPorId(String id) {
        return pedidoRepository.findById(id);
    }
}
