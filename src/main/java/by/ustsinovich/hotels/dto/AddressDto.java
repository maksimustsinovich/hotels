package by.ustsinovich.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record AddressDto(
        @NotNull(message = "House number is required")
        @Positive(message = "House number must be positive")
        Long houseNumber,

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "Country is required")
        String country,

        @NotBlank(message = "Post code is required")
        String postCode
) {
}