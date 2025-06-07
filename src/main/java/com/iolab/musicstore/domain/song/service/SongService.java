package com.iolab.musicstore.domain.song.service;

import com.iolab.musicstore.application.dto.response.CollectionDataResponse;
import com.iolab.musicstore.application.dto.response.PageInfo;
import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.domain.song.dto.SongCreateDto;
import com.iolab.musicstore.domain.song.dto.SongInfoDto;
import com.iolab.musicstore.domain.song.entity.Song;
import com.iolab.musicstore.domain.song.repository.SongRepository;
import com.iolab.musicstore.infrastructure.util.FileAttachUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import static org.springframework.http.HttpHeaders.*;

@Service
@AllArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public CollectionDataResponse<SongInfoDto> getSongs(int page,
                                                        int size ,
                                                        String sortParam) {
//        String[] split = sortParam.split(":");
//        Sort sort = Sort.by(split[0]);
//        if (split.length == 1 || "asc".equalsIgnoreCase(split[1])) {
//            sort.ascending();
//        } else if (("desc").equalsIgnoreCase(split[1])) {
//            sort.descending();
//        }

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<Song> pageData = songRepository.getSong(pageRequest);

        List<SongInfoDto> songInfoDtos = pageData.toList()
                                                    .stream()
                                                    .map(SongInfoDto::convert)
                                                    .toList();

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(pageData.getPageable().getPageNumber() + 1)
                .total(pageData.getTotalPages())
                .size(pageData.getPageable().getPageSize())
                .sortBy("id")
                .sortOrder("asc")
                .build();

        return CollectionDataResponse.success(songInfoDtos, pageInfo);
    }

    @Transactional
    public SingleDataResponse<SongInfoDto> createNewSong(SongCreateDto songCreateDto) {
        Song newSong = new Song();
        // #TODO album
        newSong.setName(songCreateDto.getName());
        newSong.setLyric(songCreateDto.getLyric());
        // #TODO package type
        // #TODO music genre
        // #TODO release date
        // #TODO album
        newSong.setCoverImage(songCreateDto.getCoverImageName());
        newSong.setAudioFile(songCreateDto.getAudioFilename());
        newSong = songRepository.save(newSong);

        SongInfoDto dto = SongInfoDto.convert(newSong);

        return SingleDataResponse.success(dto);
    }

    public void playAudioFile(Long songId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));

        File audioFile = FileAttachUtil.getAudioFile(song.getAudioFile());
        long fileLength = audioFile.length();

        // Lấy Range header từ request
        String rangeHeader = request.getHeader("Range");

        if (rangeHeader == null || !rangeHeader.startsWith("bytes=")) {
            // Không có Range header, trả về toàn bộ file
            response.setStatus(HttpServletResponse.SC_OK); // 200
            response.setContentType("audio/mpeg"); // Sửa thành audio/mp4
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(fileLength));

            try (FileInputStream fis = new FileInputStream(audioFile)) {
                fis.transferTo(response.getOutputStream());
            }
        } else {
            // Xử lý Range request
            String rangeValue = rangeHeader.substring(6); // Bỏ "bytes="
            String[] ranges = rangeValue.split("-");

            long start = 0;
            long end = fileLength - 1;

            if (ranges.length > 0 && !ranges[0].isEmpty()) {
                start = Long.parseLong(ranges[0]);
            }

            if (ranges.length > 1 && !ranges[1].isEmpty()) {
                end = Long.parseLong(ranges[1]);
            }

            // Đảm bảo end không vượt quá file size
            if (end >= fileLength) {
                end = fileLength - 1;
            }

            long contentLength = end - start + 1;

            // Set response headers cho partial content
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206
            response.setContentType("audio/mpeg");
            response.setHeader(ACCEPT_RANGES, "bytes");
            response.setHeader(CONTENT_LENGTH, String.valueOf(contentLength));
            response.setHeader(CONTENT_RANGE, String.format("bytes %d-%d/%d", start, end, fileLength));

            // Đọc và ghi partial content
            try (RandomAccessFile raf = new RandomAccessFile(audioFile, "r")) {
                raf.seek(start);

                byte[] buffer = new byte[4096]; // 1KB buffer
                long bytesToRead = contentLength;

                while (bytesToRead > 0) {
                    int readSize = (int) Math.min(buffer.length, bytesToRead);
                    int bytesRead = raf.read(buffer, 0, readSize);

                    if (bytesRead == -1) break;

                    response.getOutputStream().write(buffer, 0, bytesRead);
                    bytesToRead -= bytesRead;
                }
            }
        }
    }
}
