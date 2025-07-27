package by.ustsinovich.hotels.service;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;

public interface HotelService {

    HotelPreviewDto createHotel(CreateHotelDto createHotelDto);

}
