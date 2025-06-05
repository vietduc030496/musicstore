package com.iolab.musicstore.musicstore.domain.artist.entity.converter;

import com.iolab.musicstore.musicstore.domain.artist.entity.ArtistType;
import jakarta.persistence.AttributeConverter;

public class ArtistTypeConverter implements AttributeConverter<ArtistType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ArtistType attribute) {
        return attribute != null ? attribute.getType() : null;
    }

    @Override
    public ArtistType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (ArtistType type : ArtistType.values()) {
            if (type.getType() == dbData) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ArtistType code: " + dbData);
    }
}
