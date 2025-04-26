package com.azsoftware.envreader.reader;


public class EnvException extends RuntimeException {

    public EnvException(Throwable cause) {
        super(cause);
    }

    public EnvException(String reason) {
        super(reason);
    }

}
