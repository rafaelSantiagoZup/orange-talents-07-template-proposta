package com.zupedu.zupmicroservices.proposta;

public enum StatusFromServer {
    COM_RESTRICAO,SEM_RESTRICAO;

    public Status statusElegibilidade(){
        if(this.equals(COM_RESTRICAO)){
            return Status.NAO_ELEGIVEL;
        }
        return Status.ELEGIVEL;

    }
}
