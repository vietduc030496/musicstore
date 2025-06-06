package com.iolab.musicstore.domain.artist.entity;

import com.iolab.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.domain.song.entity.Song;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artist_song")
@Getter
@Setter
public class ArtistSong extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_song_id")
    private Long artistSongId;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

}
