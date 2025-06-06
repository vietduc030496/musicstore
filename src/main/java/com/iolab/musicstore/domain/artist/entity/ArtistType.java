package com.iolab.musicstore.domain.artist.entity;

import lombok.Getter;

@Getter
public enum ArtistType {

    SINGLE(0, "artist.type.single"),
    BAND(1, "artist.type.band"),
    COMPOSER(2, "artist.type.composer");


    private final int type;
    private final String nameCode;

    ArtistType(int type, String nameCode) {
        this.type = type;
        this.nameCode = nameCode;
    }
}
