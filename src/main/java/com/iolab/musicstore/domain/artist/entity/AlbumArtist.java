package com.iolab.musicstore.domain.artist.entity;

import com.iolab.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.domain.song.entity.Album;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "album_artist")
@Getter
@Setter
public class AlbumArtist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_artist_id")
    private Long albumArtistId;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}
