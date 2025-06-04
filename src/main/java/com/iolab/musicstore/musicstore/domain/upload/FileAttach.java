package com.iolab.musicstore.musicstore.domain.upload;

import com.iolab.musicstore.musicstore.domain.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file_attach")
@Getter
@Setter
public class FileAttach extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileAttachId;

    private String data;

    private String extension;
}
