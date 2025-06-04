package com.iolab.musicstore.musicstore.domain.song.dto;

import com.iolab.musicstore.musicstore.domain.song.entity.Song;
import com.iolab.musicstore.musicstore.infrastructure.util.DateFormatUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongInfoDto {

    private long songId;
    private String name;
    private String lyric;
    private String packageType;
    private int listenCount;
    private String musicGenre;
    private String releaseDate;
    private String albumUrl; // #TODO hateoas
    private String coverImageUrl; // #TODO hateoas
    private String audioUrl; // #TODO hateoas

    public static SongInfoDto convert(Song song) {
        SongInfoDto dto = new SongInfoDto();
        dto.setSongId(song.getSongId());
        dto.setName(song.getName());
        dto.setLyric(song.getLyric());
        dto.setPackageType(song.getPackageType().getNameCode());
        dto.setListenCount(song.getListenCount());
        dto.setMusicGenre(song.getMusicGenre().getName());
        dto.setReleaseDate(DateFormatUtil.formatDateToString(song.getReleaseDate()));

        return dto;
    }
}
