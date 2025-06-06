package com.iolab.musicstore.domain.upload.annotation;

//public class FileExistValidator implements ConstraintValidator<FileExist, Long> {
//
//    @Autowired
//    private FileAttachRepository fileAttachRepository;
//
//    @Override
//    public void initialize(FileExist constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(Long value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return true;
//        }
//
//        return fileAttachRepository.existsById(value);
//    }
//}
