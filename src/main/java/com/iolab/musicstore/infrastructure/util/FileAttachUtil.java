package com.iolab.musicstore.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

@Component
@Slf4j
public class FileAttachUtil {

    private static final String PROJECT_ROOT = System.getProperty("user.dir");

    private static String audioPath;
    private static String imagePath;
    private static String videoPath;

    private static final Map<String, String> extensionToFolder = new HashMap<>();

    @Value("${upload.path.audio}")
    public void setAudioPath(String audioPath) {
        // AUDIO
        FileAttachUtil.audioPath = audioPath;
        File audioDir = new File(PROJECT_ROOT + File.separator + audioPath);
        log.info("Audio path: {}", audioDir.getAbsolutePath());
        boolean mkdir = audioDir.mkdir();
        log.info("Audio dir created: {}", mkdir);

        extensionToFolder.put("mp3", audioPath);
        extensionToFolder.put("wav", audioPath);
        extensionToFolder.put("aac", audioPath);
    }

    @Value("${upload.path.image}")
    public void setImagePath(String imagePath) {
        FileAttachUtil.imagePath = imagePath;
        File audioDir = new File(PROJECT_ROOT + File.separator + imagePath);
        boolean mkdirs = audioDir.mkdirs();
        // IMAGE
        extensionToFolder.put("jpg", imagePath);
        extensionToFolder.put("jpeg", imagePath);
        extensionToFolder.put("png", imagePath);
        extensionToFolder.put("gif", imagePath);
    }

    @Value("${upload.path.video}")
    public void setVideoPath(String videoPath) {
        FileAttachUtil.videoPath = videoPath;
        File audioDir = new File(PROJECT_ROOT + File.separator + videoPath);
        audioDir.mkdir();
        // VIDEO
        extensionToFolder.put("mp4", videoPath);
        extensionToFolder.put("avi", videoPath);
        extensionToFolder.put("mov", videoPath);
        extensionToFolder.put("mkv", videoPath);
    }

    public static String saveUploadFile(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "." + extension;

        String folderPath = extensionToFolder.get(extension);

        String uploadDirPath = new StringJoiner(File.separator)
                                                .add(PROJECT_ROOT)
                                                .add(folderPath)
                                                .toString();

        file.transferTo(new File(uploadDirPath + File.separator + fileName));

        return fileName;
    }

    public static File getAudioFile(String audioFileName) throws FileNotFoundException {
        String projectRoot = System.getProperty("user.dir");
        String audioFolderPath = new StringJoiner(File.separator)
                                                .add(projectRoot)
                                                .add(audioPath)
                                                .add(audioFileName)
                                                .toString();

        File audioFile = new File(audioFolderPath);

        if (!audioFile.exists()) {
            throw new FileNotFoundException("Song not found");
        }

        return audioFile;
    }
}
