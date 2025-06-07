package com.iolab.musicstore.api.rest.artist;

import com.iolab.musicstore.domain.artist.dto.ArtistInfoDto;
import com.iolab.musicstore.domain.artist.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{artist-id}")
    public ResponseEntity<ArtistInfoDto> getArtistById(@PathVariable("artist-id") String artistId) {
        artistService
    }
}
