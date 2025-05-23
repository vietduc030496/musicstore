package com.iolab.musicstore.musicstore.application.dto.response;

import com.iolab.musicstore.musicstore.infrastructure.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

import static com.iolab.musicstore.musicstore.infrastructure.constant.MessageCode.SUCCESS_MSG;

@Getter
@Setter
public class CollectionDataResponse<T> extends BaseResponse {
    private PageInfo pageInfo;
    private Collection<T> list;

    public static <T> CollectionDataResponse<T> success(Collection<T> list,  PageInfo pageInfo) {
        var response = new CollectionDataResponse<T>();
        response.list = list;
        response.pageInfo = pageInfo;
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(MessageUtil.getMessage(SUCCESS_MSG));
        response.setType(ResponseType.SUCCESS);
        return response;
    }
}
