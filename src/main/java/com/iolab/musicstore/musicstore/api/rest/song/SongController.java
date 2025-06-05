package com.iolab.musicstore.musicstore.api.rest.song;

import com.iolab.musicstore.musicstore.application.dto.response.CollectionDataResponse;
import com.iolab.musicstore.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.musicstore.domain.song.dto.SongCreateDto;
import com.iolab.musicstore.musicstore.domain.song.dto.SongInfoDto;
import com.iolab.musicstore.musicstore.domain.song.service.SongService;
import com.iolab.musicstore.musicstore.domain.upload.dto.FileAttachInfoDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<CollectionDataResponse<SongInfoDto>> getSongs(
                                    @RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false, defaultValue = "song_id:asc") String sort) {

        return ResponseEntity.ok(songService.getSongs(page, size, sort));
    }

    @PostMapping
    public ResponseEntity<Void> createSong(@RequestBody @Valid SongCreateDto songCreateDto) {
//        return ResponseEntity.ok(songService.getSongs(1, 10, "song_id:asc"));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/audio")
    public ResponseEntity<SingleDataResponse<FileAttachInfoDto>> uploadAudio(@RequestParam("audio") MultipartFile audio) throws IOException {
        return ResponseEntity.ok(songService.uploadAudio(audio));
    }
}
