package by.ustsinovich.hotels.strategy.histogram;

import by.ustsinovich.hotels.entity.Hotel;

import java.util.Map;

public interface HistogramStrategy {

    Map<String, Long> calculate(Iterable<Hotel> hotels);

}
