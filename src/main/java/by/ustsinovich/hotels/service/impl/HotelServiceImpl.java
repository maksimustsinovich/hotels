package by.ustsinovich.hotels.service.impl;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.mapper.HotelMapper;
import by.ustsinovich.hotels.repository.HotelRepository;
import by.ustsinovich.hotels.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelMapper hotelMapper;

    private final HotelRepository hotelRepository;

    @Override
    @Transactional
    public HotelPreviewDto createHotel(CreateHotelDto createHotelDto) {
        var hotel = hotelMapper.fromDto(createHotelDto);

        return hotelMapper.toPreviewDto(hotelRepository.save(hotel));
    }

}
