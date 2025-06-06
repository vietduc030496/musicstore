package com.iolab.musicstore.domain.artist.entity;

import com.iolab.musicstore.domain.artist.entity.converter.ArtistTypeConverter;
import com.iolab.musicstore.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artist")
@Getter
@Setter
public class Artist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long artistId;

    private String name;

    @Convert(converter = ArtistTypeConverter.class)
    private ArtistType type;

    private String description;
}
