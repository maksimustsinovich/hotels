package by.ustsinovich.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ContactInfoDto(
        @NotBlank(message = "Phone is required")
        String phone,

        @NotBlank(message = "Email is required")
        String email
) {
}
