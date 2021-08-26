package com.zupedu.zupmicroservices.configuration;

import com.zupedu.zupmicroservices.cartao.BloqueioRepository;
import com.zupedu.zupmicroservices.proposta.PropostaRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MinhasMetricas {


    private final MeterRegistry meterRegistry;
    private final PropostaRepository propostaRepository;
    private final BloqueioRepository bloqueioRepository;

    public MinhasMetricas(MeterRegistry meterRegistry, PropostaRepository propostaRepository, BloqueioRepository bloqueioRepository) {
        this.meterRegistry = meterRegistry;
        this.propostaRepository = propostaRepository;
        this.bloqueioRepository = bloqueioRepository;
    }

    @Scheduled(fixedDelayString = "${metric.delay.gauge}")
    private void criarGauge() {
        this.meterRegistry.gauge("propostas_gauge", propostaRepository.findAll(), Collection::size);
        this.meterRegistry.gauge("cartoes_bloqueados_gauge", bloqueioRepository.findAll(), Collection::size);

    }

    public void timerReqPropostas(Long id, JpaRepository repository){
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("Id", id.toString()));
        Timer timerConsultarProposta = this.meterRegistry.timer("consultar_proposta", tags);
        timerConsultarProposta.record(() -> {
            repository.findById(id);
        });

    }
    public void meuContadorPropostas(Long id, String status) {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("Id", id.toString()));
        tags.add(Tag.of("Status", status));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
        contadorDePropostasCriadas.increment();
    }
    public void meuContadorCartoes(Long id) {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("Id", id.toString()));
        tags.add(Tag.of("Status", "Gerado"));

        Counter contadorDeCartoesGerados = this.meterRegistry.counter("cartao_gerado", tags);
        contadorDeCartoesGerados.increment();
    }


}
