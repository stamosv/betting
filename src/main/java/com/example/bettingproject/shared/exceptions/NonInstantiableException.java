package com.example.bettingproject.shared.exceptions;

public class NonInstantiableException extends RuntimeException {

    public NonInstantiableException(String msg) {
        super(msg);
    }

    public NonInstantiableException() {
        super();
    }
}
