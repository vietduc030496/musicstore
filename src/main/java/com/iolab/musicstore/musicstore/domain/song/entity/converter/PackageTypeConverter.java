package com.iolab.musicstore.musicstore.domain.song.entity.converter;

import com.iolab.musicstore.musicstore.domain.song.entity.PackageType;
import jakarta.persistence.AttributeConverter;

public class PackageTypeConverter implements AttributeConverter<PackageType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PackageType attribute) {
        return attribute != null ? attribute.getType() : null;
    }

    @Override
    public PackageType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        for (PackageType type : PackageType.values()) {
            if (type.getType() == dbData) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown ArtistType code: " + dbData);
    }
}
