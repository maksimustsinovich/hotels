package by.ustsinovich.hotels.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum HistogramParameter {

    BRAND("brand"),
    CITY("city"),
    COUNTRY("country"),
    AMENITIES("amenities");

    private final String value;

    public static HistogramParameter fromString(String param) {
        for (HistogramParameter histogramParameter : HistogramParameter.values()) {
            if (histogramParameter.getValue().equals(param)) {
                return histogramParameter;
            }
        }

        throw new IllegalArgumentException("Unsupported histogram parameter: " + param);
    }

}

