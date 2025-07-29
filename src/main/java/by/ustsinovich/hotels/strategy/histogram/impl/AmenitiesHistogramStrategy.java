package by.ustsinovich.hotels.strategy.histogram.impl;

import by.ustsinovich.hotels.entity.Hotel;
import by.ustsinovich.hotels.strategy.histogram.HistogramStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AmenitiesHistogramStrategy implements HistogramStrategy {

    @Override
    public Map<String, Long> calculate(Iterable<Hotel> hotels) {
        return StreamSupport
                .stream(hotels.spliterator(), false)
                .flatMap(hotel -> hotel.getAmenities().stream())
                .collect(
                        Collectors.groupingBy(
                                amenity -> amenity,
                                Collectors.counting()
                        )
                );
    }

}
