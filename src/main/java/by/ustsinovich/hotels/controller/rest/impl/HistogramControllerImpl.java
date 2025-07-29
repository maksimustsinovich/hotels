package by.ustsinovich.hotels.controller.rest.impl;

import by.ustsinovich.hotels.controller.rest.HistogramController;
import by.ustsinovich.hotels.enumeration.HistogramParameter;
import by.ustsinovich.hotels.service.HistogramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HistogramControllerImpl implements HistogramController {

    private final HistogramService histogramService;

    @Override
    public Map<String, Long> getHistogramByParam(HistogramParameter param) {
        return histogramService.getHistogramByParam(param);
    }

}
