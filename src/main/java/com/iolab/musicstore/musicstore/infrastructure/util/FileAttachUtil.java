package com.iolab.musicstore.musicstore.infrastructure.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileAttachUtil {

    private static String fileAttachPath;

    @Value("${upload.path}")
    public void setFileAttachPath(String fileAttachPath) {
        FileAttachUtil.fileAttachPath = fileAttachPath;
    }

    public static String saveUploadFile(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "." + extension;

        String projectRoot = System.getProperty("user.dir");
        String uploadDirPath = projectRoot + File.separator + fileAttachPath;

        file.transferTo(new File(uploadDirPath + File.separator + fileName));

        return fileName;
    }
}
