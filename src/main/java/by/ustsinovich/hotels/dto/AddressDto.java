package by.ustsinovich.hotels.dto;

public record AddressDto(
        Long houseNumber,
        String street,
        String city,
        String country,
        String postCode
) {
}
