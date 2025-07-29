package by.ustsinovich.hotels.strategy.histogram;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import by.ustsinovich.hotels.strategy.histogram.impl.AmenitiesHistogramStrategy;
import by.ustsinovich.hotels.strategy.histogram.impl.BrandHistogramStrategy;
import by.ustsinovich.hotels.strategy.histogram.impl.CityHistogramStrategy;
import by.ustsinovich.hotels.strategy.histogram.impl.CountryHistogramStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HistogramStrategyFactory {

    private final BrandHistogramStrategy brandHistogramStrategy;

    private final CityHistogramStrategy cityHistogramStrategy;

    private final CountryHistogramStrategy countryHistogramStrategy;

    private final AmenitiesHistogramStrategy amenitiesHistogramStrategy;

    public HistogramStrategy chooseStrategy(HistogramParameter param) {
        return switch (param) {
            case BRAND -> brandHistogramStrategy;
            case CITY -> cityHistogramStrategy;
            case COUNTRY -> countryHistogramStrategy;
            case AMENITIES -> amenitiesHistogramStrategy;
        };
    }

}
