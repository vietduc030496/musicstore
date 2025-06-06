package com.iolab.musicstore.application.dto.response;

import com.iolab.musicstore.infrastructure.util.MessageUtil;
import org.springframework.http.HttpStatus;

import static com.iolab.musicstore.infrastructure.constant.MessageCode.SUCCESS_MSG;

public class SingleDataResponse<T> extends BaseResponse {
    private T data;

    public static <T> SingleDataResponse<T> success(T data) {
        var response = new SingleDataResponse<T>();
        response.data = data;
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(MessageUtil.getMessage(SUCCESS_MSG));
        response.setType(ResponseType.SUCCESS);
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
