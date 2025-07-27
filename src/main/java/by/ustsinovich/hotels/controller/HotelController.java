package by.ustsinovich.hotels.controller;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/property-view/hotels")
public interface HotelController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    HotelPreviewDto createHotel(@Valid @RequestBody CreateHotelDto createHotelDto);

}
