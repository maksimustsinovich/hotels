package by.ustsinovich.hotels.service;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated
public interface HistogramService {

    Map<String, Long> getHistogramByParam(@NotNull(message = "Param cannot be null") HistogramParameter param);

}
