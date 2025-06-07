package com.iolab.musicstore.domain.artist.entity;

import com.iolab.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.domain.song.entity.Album;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artist_album")
@Getter
@Setter
public class ArtistAlbum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_album_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;


}
