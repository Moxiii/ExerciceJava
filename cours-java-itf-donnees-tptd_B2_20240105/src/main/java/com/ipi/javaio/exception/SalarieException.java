package com.ipi.javaio.exception;

public class SalarieException extends Exception {

    public SalarieException(String s) {
        super(s);
    }
    public SalarieException(String s, Exception e) {
        super(s, e);
    }

}
