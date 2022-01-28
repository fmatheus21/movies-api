package com.fmatheus.app.controller.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDatetUtil {

    static LocalDateTime localDateTime;

    private LocalDatetUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String converterToString(LocalDate localDate, FormatStyle formatStyle) {
        return localDate.format(DateTimeFormatter.ofLocalizedDate(formatStyle));
    }

    public static String converterToLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(localDate);
    }

    public static String converterLocalDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(localDateTime);
    }

    public static LocalDate converterStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime converterStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

    public static Long returnsMillisecondsOfDateTime() {
        localDateTime = LocalDateTime.now();
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate currentDate() {
        return LocalDate.now();
    }

    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    public static int currentYear() {
        return LocalDate.now().getYear();
    }

}
