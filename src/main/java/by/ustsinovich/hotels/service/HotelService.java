package by.ustsinovich.hotels.service;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;

import java.util.Set;

public interface HotelService {

    HotelPreviewDto createHotel(CreateHotelDto createHotelDto);

    void addAmenitiesByHotelId(Long hotelId, Set<String> amenities);

}
