package by.ustsinovich.hotels.converter;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToHistogramParameterConverter implements Converter<String, HistogramParameter> {

    @Override
    public HistogramParameter convert(String source) {
        return HistogramParameter.fromString(source);
    }

}
