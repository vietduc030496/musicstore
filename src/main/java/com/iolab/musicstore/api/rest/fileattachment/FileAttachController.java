package com.iolab.musicstore.api.rest.fileattachment;

import com.iolab.musicstore.application.dto.response.SingleDataResponse;
import com.iolab.musicstore.domain.upload.dto.FileAttachInfoDto;
import com.iolab.musicstore.infrastructure.util.FileAttachUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

@RestController
@RequestMapping("/attachments")
public class FileAttachController {

    @PostMapping("/upload")
    public ResponseEntity<SingleDataResponse<FileAttachInfoDto>> uploadAudio(@RequestParam("attachment") MultipartFile fileUpload) throws IOException {
        String fileName = FileAttachUtil.saveUploadFile(fileUpload);

        FileAttachInfoDto dto = new FileAttachInfoDto(fileName);

        return ResponseEntity.ok(SingleDataResponse.success(dto));
    }

    @GetMapping("/{filename}")
    public void getFile(@PathVariable String filename,
                                        @RequestHeader(value = "Range", required = false) String rangeHeader,
                                        HttpServletResponse response) throws IOException {
        File videoFile = new File("uploads/" + filename);

        if (!videoFile.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        long fileLength = videoFile.length();
        long start = 0;
        long end = fileLength - 1;

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            String[] ranges = rangeHeader.substring(6).split("-");
            try {
                start = Long.parseLong(ranges[0]);
                if (ranges.length > 1) {
                    end = Long.parseLong(ranges[1]);
                }
            } catch (NumberFormatException ignored) {
            }
        }

        long contentLength = end - start + 1;

        // Set các header
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206
        response.setContentType("video/mp4");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);
        response.setHeader("Content-Length", String.valueOf(contentLength));

        try (
                RandomAccessFile inputFile = new RandomAccessFile(videoFile, "r");
                OutputStream outputStream = response.getOutputStream()
        ) {
            inputFile.seek(start);

            byte[] buffer = new byte[8192];
            long remaining = contentLength;
            int bytesRead;

            while ((bytesRead = inputFile.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1 && remaining > 0) {
                outputStream.write(buffer, 0, bytesRead);
                remaining -= bytesRead;
            }
        }
    }
}
