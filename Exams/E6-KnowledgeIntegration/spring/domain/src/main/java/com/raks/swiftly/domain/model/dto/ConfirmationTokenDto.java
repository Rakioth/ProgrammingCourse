package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

@Data
@Builder
public class ConfirmationTokenDto {

    private String        email;
    private String        token;
    private LocalDateTime expiredDate;
    private Boolean       verified;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiredDate);
    }

    public static String generateToken() {
        SecureRandom random       = new SecureRandom();
        int          randomNumber = random.nextInt(9000) + 1000;
        return String.valueOf(randomNumber);
    }

    public static LocalDateTime generateExpiredDate(int minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        return now.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}