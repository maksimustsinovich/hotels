package by.ustsinovich.hotels.controller.rest.impl;

import by.ustsinovich.hotels.controller.rest.SearchController;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class SearchControllerImpl implements SearchController {

    private final SearchService searchService;

    @Override
    public List<HotelPreviewDto> search(String name, String brand, String city, String country, Set<String> amenities) {
        return searchService.search(name, brand, city, country, amenities);
    }

}
