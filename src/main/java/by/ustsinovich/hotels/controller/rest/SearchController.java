package by.ustsinovich.hotels.controller.rest;

import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;

@Validated
@RequestMapping("/property-view/search")
public interface SearchController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<HotelPreviewDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Set<String> amenities
    );

}
