package dev.destiny.afrorusbackend.dto;

import java.time.LocalDateTime;

public record SpaceAvailableForDocDto(
        String userId,
        String departureCity,
        String arrivalCity,
        Double price,
        String currency,
        LocalDateTime availableFrom,
        LocalDateTime availableTo,
        boolean isVerifiedUser
) {}
