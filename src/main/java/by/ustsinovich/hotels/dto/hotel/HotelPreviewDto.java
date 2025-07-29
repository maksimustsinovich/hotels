package by.ustsinovich.hotels.dto.hotel;

import lombok.Builder;

@Builder
public record HotelPreviewDto(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {
}
