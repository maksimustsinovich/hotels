package by.ustsinovich.hotels.controller.rest;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Validated
@RequestMapping("/property-view/histogram")
public interface HistogramController {

    @GetMapping("/{param}")
    Map<String, Long> getHistogramByParam(
            @NotNull(message = "Param cannot be null") @PathVariable HistogramParameter param
    );

}
