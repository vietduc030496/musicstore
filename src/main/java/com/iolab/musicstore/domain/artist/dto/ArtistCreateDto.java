package com.iolab.musicstore.domain.artist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistCreateDto {
    private Long id;
    private String name;

    @IsExistEnumValue
    private String artistType;
    private String avatar;
    private String description;
}
