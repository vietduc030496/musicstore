package com.iolab.musicstore.musicstore.domain.artist.entity;

import com.iolab.musicstore.musicstore.domain.base.entity.BaseEntity;
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
    private Long artistId;

    private String name;

    @Convert(converter = ArtistTypeConverter.class)
    private ArtistType type;

    private String description;
}
