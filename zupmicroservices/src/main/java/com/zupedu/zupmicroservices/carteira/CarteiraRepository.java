package com.zupedu.zupmicroservices.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Paypal,Long> {
    Optional<Paypal> findByCartaoId(String idCartao);
}
