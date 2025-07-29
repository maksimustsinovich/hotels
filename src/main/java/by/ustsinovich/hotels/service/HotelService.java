package by.ustsinovich.hotels.service;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Validated
public interface HotelService {

    HotelPreviewDto createHotel(@Valid CreateHotelDto createHotelDto);

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
