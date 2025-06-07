package com.iolab.musicstore.domain.artist.service;

import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.domain.artist.dto.ArtistInfoDto;
import com.iolab.musicstore.domain.artist.entity.Artist;
import com.iolab.musicstore.domain.artist.entity.ArtistAlbum;
import com.iolab.musicstore.domain.artist.repository.ArtistAlbumRepository;
import com.iolab.musicstore.domain.artist.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final ArtistAlbumRepository artistAlbumRepository;

    public SingleDataResponse<ArtistInfoDto> getArtistInfo(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new IllegalArgumentException("Artist not found"));

        PageRequest firstTenAlbumOfArtist = PageRequest.of(0, 10);
        artistAlbumRepository.findAlbumByArtistId(artistId, firstTenAlbumOfArtist);
//        return SingleDataResponse.success()
        return null;
    }

}
