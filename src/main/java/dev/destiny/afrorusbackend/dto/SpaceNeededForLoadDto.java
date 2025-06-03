package dev.destiny.afrorusbackend.dto;

import java.time.LocalDateTime;

public record SpaceNeededForLoadDto(
        String userId,
        String departureCity,
        String arrivalCity,
        Double weightKg,
        String description,
        LocalDateTime availableFrom,
        LocalDateTime availableTo
) {}
