package by.ustsinovich.hotels.controller.impl;

import by.ustsinovich.hotels.controller.HotelController;
import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HotelControllerImpl implements HotelController {

    private final HotelService hotelService;

    @Override
    public HotelPreviewDto createHotel(CreateHotelDto createHotelDto) {
        return hotelService.createHotel(createHotelDto);
    }

}
