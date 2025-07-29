package by.ustsinovich.hotels.service.impl;

import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.entity.Hotel;
import by.ustsinovich.hotels.mapper.HotelMapper;
import by.ustsinovich.hotels.repository.HotelRepository;
import by.ustsinovich.hotels.service.SearchService;
import by.ustsinovich.hotels.specification.HotelSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Validated
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HotelPreviewDto> search(String name, String brand, String city, String country, Set<String> amenities) {
        Specification<Hotel> specification = HotelSpecification.hasName(name)
                .and(HotelSpecification.hasBrand(brand))
                .and(HotelSpecification.hasCity(city))
                .and(HotelSpecification.hasCountry(country))
                .and(HotelSpecification.hasAmenities(amenities));

        return hotelRepository.findAll(specification).stream().map(hotelMapper::toPreviewDto).toList();
    }

}
