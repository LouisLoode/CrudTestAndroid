package com.borislaporte.lasalle.model;

/**
 * Created by moi on 26/04/2017.
 */

public class BaseResponse {

    private int statusCode;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
