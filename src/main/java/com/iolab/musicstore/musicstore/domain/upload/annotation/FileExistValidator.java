package com.iolab.musicstore.musicstore.domain.upload.annotation;

import com.iolab.musicstore.musicstore.domain.upload.repository.FileAttachRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class FileExistValidator implements ConstraintValidator<FileExist, Long> {

    @Autowired
    private FileAttachRepository fileAttachRepository;

    @Override
    public void initialize(FileExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return fileAttachRepository.existsById(value);
    }
}
