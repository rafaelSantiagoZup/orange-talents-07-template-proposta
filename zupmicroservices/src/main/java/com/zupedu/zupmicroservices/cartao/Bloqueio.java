package com.zupedu.zupmicroservices.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ids", nullable = false)
        private Long id;

        private String cartaoId;
        private LocalDateTime bloqueadoEm = LocalDateTime.now();
        private String sistemaResponsavel;
        private boolean ativo;

        public Long getIds() {
            return id;
        }


        public Bloqueio() {
        }

        public Bloqueio(String id, String sistemaResponsavel, String resposta) {
            this.cartaoId = id;
            this.sistemaResponsavel = sistemaResponsavel;
            this.ativo = estaAtivo(resposta);
        }
        public boolean estaAtivo(String resposta){
            if(resposta == "BLOQUEADO"){
                return false;
            }
            return true;
        }
        public String getCartaoId() {
            return cartaoId;
        }

        public void setId(String id) {
            this.cartaoId = id;
        }

        public LocalDateTime getBloqueadoEm() {
            return bloqueadoEm;
        }

        public void setBloqueadoEm(LocalDateTime bloqueadoEm) {
            this.bloqueadoEm = bloqueadoEm;
        }

        public String getSistemaResponsavel() {
            return sistemaResponsavel;
        }

        public void setSistemaResponsavel(String sistemaResponsavel) {
            this.sistemaResponsavel = sistemaResponsavel;
        }

        public boolean isAtivo() {
            return ativo;
        }

        public void setAtivo(boolean ativo) {
            this.ativo = ativo;
        }

}
