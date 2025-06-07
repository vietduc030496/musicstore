package com.iolab.musicstore.domain.artist.service;

import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.domain.artist.dto.AlbumByArtistInfoDto;
import com.iolab.musicstore.domain.artist.dto.ArtistCreateDto;
import com.iolab.musicstore.domain.artist.dto.ArtistInfoDto;
import com.iolab.musicstore.domain.artist.entity.Artist;
import com.iolab.musicstore.domain.artist.entity.ArtistAlbum;
import com.iolab.musicstore.domain.artist.repository.ArtistAlbumRepository;
import com.iolab.musicstore.domain.artist.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final ArtistAlbumRepository artistAlbumRepository;

    public SingleDataResponse<ArtistInfoDto> getArtistInfo(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new IllegalArgumentException("Artist not found"));

        var firstTenAlbumOfArtist = PageRequest.of(0, 10);
        var listAlbumByArtist = artistAlbumRepository.findAlbumByArtistId(artistId, firstTenAlbumOfArtist);
//        return SingleDataResponse.success()

        var artistInfoDto = ArtistInfoDto.convert(artist);
        artistInfoDto.setAlbumInfo(listAlbumByArtist);

        return SingleDataResponse.success(artistInfoDto);
    }

    public SingleDataResponse<ArtistCreateDto> createNewArtist(ArtistCreateDto artistCreateDto) {
        Artist newArtist = new Artist();
        newArtist.setName(artistCreateDto.getName());
//        newArtist.setType(artistCreateDto.getType()); #TODO
        newArtist.setAvatar(artistCreateDto.getAvatar());
        newArtist.setDescription(artistCreateDto.getDescription());

        newArtist = artistRepository.save(newArtist);

        return SingleDataResponse.success(artistCreateDto);
    }

}
