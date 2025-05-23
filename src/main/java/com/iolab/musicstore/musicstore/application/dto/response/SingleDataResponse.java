package com.iolab.musicstore.musicstore.application.dto.response;

import com.iolab.musicstore.musicstore.infrastructure.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static com.iolab.musicstore.musicstore.infrastructure.constant.MessageCode.SUCCESS_MSG;

@Setter
@Getter
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

}
