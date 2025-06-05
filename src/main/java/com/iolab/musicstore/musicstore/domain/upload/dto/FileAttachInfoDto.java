package com.iolab.musicstore.musicstore.domain.upload.dto;

import com.iolab.musicstore.musicstore.domain.upload.entity.FileAttach;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileAttachInfoDto {

    private Long fileAttachId;

    private String data;

    private String extension;

    public static FileAttachInfoDto convert(FileAttach fileAttach) {
        FileAttachInfoDto fileAttachInfoDto = new FileAttachInfoDto();
        fileAttachInfoDto.setFileAttachId(fileAttach.getFileAttachId());
        fileAttachInfoDto.setData(fileAttach.getData());
        fileAttachInfoDto.setExtension(fileAttach.getExtension());
        return fileAttachInfoDto;
    }
}
