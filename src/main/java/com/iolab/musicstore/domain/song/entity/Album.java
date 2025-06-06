package com.iolab.musicstore.domain.song.entity;

import com.iolab.musicstore.domain.artist.entity.AlbumArtist;
import com.iolab.musicstore.domain.base.entity.BaseEntity;
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

    @Column(name = "cover_image")
    private String coverImage;

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
