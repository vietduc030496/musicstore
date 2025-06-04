package com.iolab.musicstore.musicstore.domain.song.entity;

import com.iolab.musicstore.musicstore.domain.artist.entity.AlbumArtist;
import com.iolab.musicstore.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.musicstore.domain.upload.FileAttach;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
@Getter
@Setter
public class Album extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_image_id")
    private FileAttach coverImage;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "total_tracks", columnDefinition = "INTEGER default 0")
    private int totalTracks;

    private String description;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AlbumArtist> albumArtists = new ArrayList<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Song> songs = new ArrayList<>();

}
