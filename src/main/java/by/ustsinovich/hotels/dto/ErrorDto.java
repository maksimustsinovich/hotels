package by.ustsinovich.hotels.dto;

import lombok.Builder;

@Builder
public record ErrorDto(
        Long timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
