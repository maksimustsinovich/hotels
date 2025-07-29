package by.ustsinovich.hotels.histogram;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;
import by.ustsinovich.hotels.enumeration.HistogramParameter;
import by.ustsinovich.hotels.service.HistogramService;
import by.ustsinovich.hotels.service.HotelService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Assertions;

import java.util.Map;
import java.util.Set;

@DisplayName("Get histogram")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetHistogramTests {

    @Autowired
    private HistogramService histogramService;

    @Autowired
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        createTestHotels();
    }

    @Test
    @DisplayName("Get histogram by brand parameter - Success")
    void shouldReturnBrandHistogramWhenBrandParamProvided() {
        // Act
        Map<String, Long> result = histogramService.getHistogramByParam(HistogramParameter.BRAND);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsKey("Hilton"));
        Assertions.assertTrue(result.containsKey("The Ritz-Carlton"));
        Assertions.assertTrue(result.containsKey("Marriott"));
    }

    @Test
    @DisplayName("Get histogram by city parameter - Success")
    void shouldReturnCityHistogramWhenCityParamProvided() {
        // Act
        Map<String, Long> result = histogramService.getHistogramByParam(HistogramParameter.CITY);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsKey("Minsk"));
        Assertions.assertEquals(3L, result.get("Minsk"));
    }

    @Test
    @DisplayName("Get histogram by country parameter - Success")
    void shouldReturnCountryHistogramWhenCountryParamProvided() {
        // Act
        Map<String, Long> result = histogramService.getHistogramByParam(HistogramParameter.COUNTRY);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsKey("Belarus"));
        Assertions.assertEquals(3L, result.get("Belarus"));
    }

    @Test
    @DisplayName("Get histogram by amenities parameter - Success")
    void shouldReturnAmenitiesHistogramWhenAmenitiesParamProvided() {
        // Act
        Map<String, Long> result = histogramService.getHistogramByParam(HistogramParameter.AMENITIES);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.containsKey("Free WiFi"));
        Assertions.assertTrue(result.containsKey("Free parking"));
        Assertions.assertTrue(result.containsKey("Non-smoking rooms"));
    }

    @Test
    @DisplayName("Get histogram with null parameter - Constraint violation")
    void shouldThrowExceptionWhenParamIsNull() {
        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> histogramService.getHistogramByParam(null)
        );
    }

    private void createTestHotels() {
        AddressDto address1 = AddressDto.builder()
                .houseNumber(9L)
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004")
                .build();

        ContactInfoDto contacts1 = ContactInfoDto.builder()
                .phone("+375 17 309-80-00")
                .email("doubletree@hilton.com")
                .build();

        ArrivalTimeDto arrivalTime1 = ArrivalTimeDto.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();

        CreateHotelDto createHotelDto1 = CreateHotelDto.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("Luxury hotel in the heart of Minsk")
                .brand("Hilton")
                .address(address1)
                .contacts(contacts1)
                .arrivalTime(arrivalTime1)
                .build();

        var hotel1 = hotelService.createHotel(createHotelDto1);

        Set<String> amenities1 = Set.of(
                "Free parking", "Free WiFi", "Non-smoking rooms",
                "Concierge", "On-site restaurant", "Fitness center", "Room service"
        );
        hotelService.addAmenitiesByHotelId(hotel1.id(), amenities1);

        AddressDto address2 = AddressDto.builder()
                .houseNumber(15L)
                .street("Nezavisimosti Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220030")
                .build();

        ContactInfoDto contacts2 = ContactInfoDto.builder()
                .phone("+375 17 289-10-00")
                .email("ritzcarlton@minsk.com")
                .build();

        ArrivalTimeDto arrivalTime2 = ArrivalTimeDto.builder()
                .checkIn("15:00")
                .checkOut("11:00")
                .build();

        CreateHotelDto createHotelDto2 = CreateHotelDto.builder()
                .name("The Ritz-Carlton Minsk")
                .description("Premium luxury accommodation")
                .brand("The Ritz-Carlton")
                .address(address2)
                .contacts(contacts2)
                .arrivalTime(arrivalTime2)
                .build();

        var hotel2 = hotelService.createHotel(createHotelDto2);

        Set<String> amenities2 = Set.of(
                "Free WiFi", "Non-smoking rooms", "Concierge",
                "On-site restaurant", "Spa services", "Business center", "Meeting rooms"
        );
        hotelService.addAmenitiesByHotelId(hotel2.id(), amenities2);

        AddressDto address3 = AddressDto.builder()
                .houseNumber(5L)
                .street("Karl Marx Street")
                .city("Minsk")
                .country("Belarus")
                .postCode("220030")
                .build();

        ContactInfoDto contacts3 = ContactInfoDto.builder()
                .phone("+375 17 200-50-00")
                .email("marriott@minsk.com")
                .build();

        ArrivalTimeDto arrivalTime3 = ArrivalTimeDto.builder()
                .checkIn("13:00")
                .checkOut("12:00")
                .build();

        CreateHotelDto createHotelDto3 = CreateHotelDto.builder()
                .name("Minsk Marriott Hotel")
                .description("Modern business hotel")
                .brand("Marriott")
                .address(address3)
                .contacts(contacts3)
                .arrivalTime(arrivalTime3)
                .build();

        var hotel3 = hotelService.createHotel(createHotelDto3);

        Set<String> amenities3 = Set.of(
                "Free parking", "Free WiFi", "Business center",
                "Meeting rooms", "Fitness center"
        );
        hotelService.addAmenitiesByHotelId(hotel3.id(), amenities3);
    }

}