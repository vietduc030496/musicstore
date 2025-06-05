package com.iolab.musicstore.musicstore.domain.song.dto;

import com.iolab.musicstore.musicstore.domain.upload.annotation.FileExist;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongCreateDto {

    @NotBlank
    private String name;
    private String lyric;

    private String packageType;
    private String musicGenre;

    private String releaseDate;
    private Long albumId;
    private Long coverImageId;

    @FileExist
    private Long audioFileId;
}
