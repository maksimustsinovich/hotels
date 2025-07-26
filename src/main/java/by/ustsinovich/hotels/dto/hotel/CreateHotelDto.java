package by.ustsinovich.hotels.dto.hotel;

import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;

public record CreateHotelDto(
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactInfoDto contacts,
        ArrivalTimeDto arrivalTime
) {
}
