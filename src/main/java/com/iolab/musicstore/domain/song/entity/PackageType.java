package com.iolab.musicstore.domain.song.entity;

import lombok.Getter;

@Getter
public enum PackageType {

    FREE(0, "package.type.free"),
    PLUS(1, "package.type.plus"),
    PREMIUM(2, "package.type.premium");


    private final int type;
    private final String nameCode;

    PackageType(int type, String nameCode) {
        this.type = type;
        this.nameCode = nameCode;
    }
}
