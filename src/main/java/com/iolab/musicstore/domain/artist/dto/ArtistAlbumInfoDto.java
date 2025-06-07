package com.iolab.musicstore.domain.artist.dto;

import com.iolab.musicstore.application.dto.response.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArtistAlbumInfoDto {

    private List<AlbumByArtistInfoDto> albums;

    private PageInfo pageInfo;
}
