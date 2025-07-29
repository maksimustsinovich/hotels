package by.ustsinovich.hotels.service;

import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;

import java.util.List;
import java.util.Set;

public interface SearchService {

    List<HotelPreviewDto> search(String name, String brand, String city, String country, Set<String> amenities);

}
