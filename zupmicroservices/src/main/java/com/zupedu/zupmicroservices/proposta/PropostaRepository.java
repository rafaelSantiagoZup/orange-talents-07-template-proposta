package com.zupedu.zupmicroservices.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta,Long> {
    Optional<Proposta> findByDocumento(String documento);
    Optional<Proposta> findByCartao(String cartao);
}
