package dev.destiny.afrorusbackend.dto;

import java.time.LocalDateTime;

public record SpaceNeedeForDocDto(
        String userId,
        String departureCity,
        String arrivalCity,
        String description,
        LocalDateTime availableFrom,
        LocalDateTime availableTo
) {}
