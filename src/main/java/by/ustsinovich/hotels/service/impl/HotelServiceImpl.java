package by.ustsinovich.hotels.service.impl;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.exception.ResourceNotFoundException;
import by.ustsinovich.hotels.mapper.HotelMapper;
import by.ustsinovich.hotels.repository.HotelRepository;
import by.ustsinovich.hotels.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    @Override
    @Transactional
    public void addAmenitiesByHotelId(Long hotelId, Set<String> amenities) {
        var hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Hotel with ID `%d` not found".formatted(hotelId))
                );

        hotel.getAmenities().addAll(amenities);

        hotelRepository.save(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelPreviewDto> getAllHotels() {
        return hotelRepository.findAll().stream().map(hotelMapper::toPreviewDto).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public HotelDto getHotelById(Long hotelId) {
        var hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Hotel with ID `%d` not found".formatted(hotelId))
                );

        return hotelMapper.toDto(hotel);
    }

}
