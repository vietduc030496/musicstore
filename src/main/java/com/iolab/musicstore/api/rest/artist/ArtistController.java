package com.iolab.musicstore.api.rest.artist;

import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.domain.artist.dto.ArtistCreateDto;
import com.iolab.musicstore.domain.artist.dto.ArtistInfoDto;
import com.iolab.musicstore.domain.artist.service.ArtistService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/{artist-id}")
    public ResponseEntity<SingleDataResponse<ArtistInfoDto>> getArtistById(@PathVariable("artist-id") Long artistId) {
        return ResponseEntity.ok(artistService.getArtistInfo(artistId));
    }

    @PostMapping
    public ResponseEntity<SingleDataResponse<ArtistCreateDto>> createNewArtist(@RequestBody @Valid ArtistCreateDto artistCreateDto) {
        return ResponseEntity.ok(artistService.createNewArtist(artistCreateDto));
    }
}
