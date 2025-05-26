package com.iolab.musicstore.musicstore.application.dto.response;

import lombok.Getter;
import lombok.Setter;

public abstract class BaseResponse {
    private int statusCode;
    private String message;
    private ResponseType type;

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

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }
}
