package com.iolab.musicstore.musicstore.domain.song.entity;

import com.iolab.musicstore.musicstore.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "music_genre")
@Getter
@Setter
public class MusicGenre extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_genre_id")
    private Long musicGenreId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
