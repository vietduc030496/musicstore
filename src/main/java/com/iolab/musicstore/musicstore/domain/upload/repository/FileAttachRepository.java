package com.iolab.musicstore.musicstore.domain.upload.repository;

import com.iolab.musicstore.musicstore.domain.upload.entity.FileAttach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttachRepository extends JpaRepository<FileAttach, Long> {
}
