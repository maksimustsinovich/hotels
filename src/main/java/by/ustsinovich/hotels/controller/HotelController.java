package by.ustsinovich.hotels.controller;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/property-view/hotels")
public interface HotelController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    HotelPreviewDto createHotel(@Valid @RequestBody CreateHotelDto createHotelDto);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{hotelId}/amenities")
    void addAmenitiesByHotelId(
            @Valid
            @NotNull(message = "Hotel ID cannot be null")
            @Positive(message = "Hotel ID must be positive")
            @PathVariable Long hotelId,
            @Valid
            @NotNull(message = "Amenities cannot be null")
            @NotEmpty(message = "Amenities cannot be empty")
            @RequestBody Set<String> amenities
    );

}
