package com.zupedu.zupmicroservices.cartao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioRepository extends JpaRepository<Bloqueio,Long> {
    Optional<Bloqueio> findByCartaoId(String id);
}
