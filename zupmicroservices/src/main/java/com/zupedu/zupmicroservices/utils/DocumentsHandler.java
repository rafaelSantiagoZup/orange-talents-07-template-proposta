package com.zupedu.zupmicroservices.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface DocumentsHandler {
    public String encriptaDados(String documento);
    public boolean checaDocumento(String documento);
}
