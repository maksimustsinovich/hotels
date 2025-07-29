package by.ustsinovich.hotels.config;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, HistogramParameter.class, HistogramParameter::fromString);
    }

}
