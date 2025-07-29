package by.ustsinovich.hotels.service.impl;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import by.ustsinovich.hotels.repository.HotelRepository;
import by.ustsinovich.hotels.service.HistogramService;
import by.ustsinovich.hotels.strategy.histogram.HistogramStrategy;
import by.ustsinovich.hotels.strategy.histogram.HistogramStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HistogramServiceImpl implements HistogramService {

    private final HotelRepository hotelRepository;

    private final HistogramStrategyFactory histogramStrategyFactory;

    @Override
    @Transactional
    public Map<String, Long> getHistogramByParam(HistogramParameter param) {
        HistogramStrategy histogramStrategy = histogramStrategyFactory.chooseStrategy(param);

        return histogramStrategy.calculate(hotelRepository.findAll());
    }

}
