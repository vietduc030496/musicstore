package com.iolab.musicstore.musicstore.domain.song.dto;

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
    private Long audioFileId;
}
