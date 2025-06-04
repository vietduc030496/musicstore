package com.iolab.musicstore.musicstore.infrastructure.util;

import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.iolab.musicstore.musicstore.infrastructure.constant.MessageCode.DATE_FORMAT_ERROR;

@Slf4j
public class DateFormatUtil {

    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";


    public static String formatDateToString(LocalDate date) {
        return formatDateToString(date, DATE_FORMAT_DEFAULT);
    }

    public static String formatDateToString(LocalDate date, String format) {
        try {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern(format);
            return date.format(pattern);
        } catch (DateTimeException e) {
            log.error(MessageUtil.getMessage(DATE_FORMAT_ERROR, format));
            throw e;
        }
    }
}
