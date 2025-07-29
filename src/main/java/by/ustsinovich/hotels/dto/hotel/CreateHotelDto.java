package by.ustsinovich.hotels.dto.hotel;

import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateHotelDto(
        @NotBlank(message = "Name is required")
        String name,

        String description,

        @NotBlank(message = "Brand is required")
        String brand,

        @NotNull(message = "Address is required")
        AddressDto address,

        @NotNull(message = "Contacts are required")
        ContactInfoDto contacts,

        @NotNull(message = "Arrival time is required")
        ArrivalTimeDto arrivalTime
) {
}