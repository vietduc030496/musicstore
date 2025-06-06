package com.iolab.musicstore.domain.song.entity;

import com.iolab.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.domain.song.entity.converter.PackageTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "song")
@Getter
@Setter
public class Song extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long songId;

    private String name;

    private String lyric;

    @Convert(converter = PackageTypeConverter.class)
    @Column(name = "package_type", columnDefinition = "INTEGER default 0")
    private PackageType packageType;

    @Column(name = "listen_count", columnDefinition = "INTEGER default 0")
    private int listenCount;

    @ManyToOne
    @JoinColumn(name = "music_genre_id")
    private MusicGenre musicGenre;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "audio_file")
    private String audioFile;

}
