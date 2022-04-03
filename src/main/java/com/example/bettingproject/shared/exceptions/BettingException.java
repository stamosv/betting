package com.example.bettingproject.shared.exceptions;

public class BettingException extends RuntimeException{

    public BettingException() {
        super();
    }

    public BettingException(String error) {
        super(error);
    }

    public BettingException(String error, Exception e) {
        super(error, e);
    }

    public BettingException(Throwable throwable) {
        super(throwable);
    }

}
