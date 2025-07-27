package by.ustsinovich.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ArrivalTimeDto(
        @NotBlank(message = "Check-in time is required")
        String checkIn,

        String checkOut
) {
}
