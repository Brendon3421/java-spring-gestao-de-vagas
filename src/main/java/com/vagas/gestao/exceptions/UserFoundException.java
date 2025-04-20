package com.vagas.gestao.exceptions;

public  class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }

}