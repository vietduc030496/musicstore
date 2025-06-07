package com.iolab.musicstore.domain.artist.dto;

import java.time.LocalDate;

public interface AlbumByArtistInfoDto {
    Long getAlbumId();

    String getName();

    String getCoverImage();

    LocalDate getReleaseDate();

    int getTotalTracks();

    String getDescription();
}
