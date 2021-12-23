package com.vinnotech.portal.exception;

public class TokenRefreshException extends RuntimeException{

    private String message;
    TokenRefreshException(String message){
        super(message);
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
