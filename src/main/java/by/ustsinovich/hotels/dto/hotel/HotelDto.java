package by.ustsinovich.hotels.dto.hotel;

import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;

import java.util.Set;

public record HotelDto(
        Long id,
        String name,
        String description,
        String brand,
        AddressDto address,
        ContactInfoDto contacts,
        ArrivalTimeDto arrivalTime,
        Set<String> amenities
) {
}
