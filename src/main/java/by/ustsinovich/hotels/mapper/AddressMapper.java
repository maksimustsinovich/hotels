package by.ustsinovich.hotels.mapper;

import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.entity.embeddable.Address;
import by.ustsinovich.hotels.mapper.qualifier.FormatAddress;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper
public interface AddressMapper {

    Address fromDto(AddressDto addressDto);

    AddressDto toDto(Address address);

    @FormatAddress
    static String formatAddress(Address address) {
        if (Objects.isNull(address)) {
            return null;
        }

        return String.format(
                "%d %s, %s, %s, %s",
                address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getPostCode(),
                address.getCountry()
        );
    }

}
