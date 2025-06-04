package com.iolab.musicstore.musicstore.domain.song.entity;

import com.iolab.musicstore.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.musicstore.domain.song.converter.PackageTypeConverter;
import com.iolab.musicstore.musicstore.domain.upload.FileAttach;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_image_id")
    private FileAttach coverImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audio_file_id")
    private FileAttach audioFile;

}
