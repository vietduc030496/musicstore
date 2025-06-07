package com.iolab.musicstore.api.rest.song;

import com.iolab.musicstore.application.dto.response.CollectionDataResponse;
import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.domain.song.dto.SongCreateDto;
import com.iolab.musicstore.domain.song.dto.SongInfoDto;
import com.iolab.musicstore.domain.song.service.SongService;
import com.iolab.musicstore.domain.upload.dto.FileAttachInfoDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<SingleDataResponse<SongInfoDto>> createSong(@RequestBody @Valid SongCreateDto songCreateDto) {
        return ResponseEntity.ok(songService.createNewSong(songCreateDto));
    }

    @GetMapping("/play/{song-id}")
    public void  playSongAudio(@PathVariable("song-id") Long songId,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        songService.playAudioFile(songId, request, response);
    }
}
