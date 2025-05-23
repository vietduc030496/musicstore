package com.iolab.musicstore.musicstore.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponse {
    private int statusCode;
    private String message;
    private ResponseType type;
}
