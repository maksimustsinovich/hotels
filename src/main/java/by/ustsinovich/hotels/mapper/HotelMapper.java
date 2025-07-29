package by.ustsinovich.hotels.mapper;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.entity.Hotel;
import by.ustsinovich.hotels.mapper.qualifier.FormatAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {AddressMapper.class, ArrivalTimeMapper.class, ContactInfoMapper.class})
public interface HotelMapper {

    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "id", ignore = true)
    Hotel fromDto(CreateHotelDto createHotelDto);

    @Mapping(target = "phone", source = "contacts.phone")
    @Mapping(target = "address", source = "address", qualifiedBy = FormatAddress.class)
    HotelPreviewDto toPreviewDto(Hotel hotel);

    HotelDto toDto(Hotel hotel);

}
