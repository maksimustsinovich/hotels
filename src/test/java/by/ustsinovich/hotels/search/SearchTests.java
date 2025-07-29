package by.ustsinovich.hotels.search;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;
import by.ustsinovich.hotels.service.HotelService;
import by.ustsinovich.hotels.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Set;

@DisplayName("Search hotels")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SearchTests {

    @Autowired
    private SearchService searchService;

    @Autowired
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        createTestHotels();
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
                "Free parking", "Free WiFi", "Non-smoking rooms"
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
                "Free WiFi", "Non-smoking rooms", "Concierge"
        );
        hotelService.addAmenitiesByHotelId(hotel2.id(), amenities2);

        AddressDto address3 = AddressDto.builder()
                .houseNumber(100L)
                .street("Tverskaya Street")
                .city("Moscow")
                .country("Russia")
                .postCode("125009")
                .build();

        ContactInfoDto contacts3 = ContactInfoDto.builder()
                .phone("+7 495 123-45-67")
                .email("hotelmoscow@hilton.com")
                .build();

        ArrivalTimeDto arrivalTime3 = ArrivalTimeDto.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();

        CreateHotelDto createHotelDto3 = CreateHotelDto.builder()
                .name("Hotel Moscow")
                .description("Luxury hotel in Moscow center")
                .brand("Hilton")
                .address(address3)
                .contacts(contacts3)
                .arrivalTime(arrivalTime3)
                .build();

        var hotel3 = hotelService.createHotel(createHotelDto3);

        Set<String> amenities3 = Set.of(
                "Free parking", "Free WiFi", "Business center"
        );
        hotelService.addAmenitiesByHotelId(hotel3.id(), amenities3);
    }

    @Test
    @DisplayName("Search hotels by name - Success")
    void shouldReturnHotelsWhenSearchingByName() {
        // Act
        List<HotelPreviewDto> result = searchService.search("DoubleTree", null, null, null, null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("DoubleTree by Hilton Minsk", result.get(0).name());
    }

    @Test
    @DisplayName("Search hotels by brand - Success")
    void shouldReturnHotelsWhenSearchingByBrand() {
        // Act
        List<HotelPreviewDto> result = searchService.search(null, "Hilton", null, null, null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(hotel -> hotel.name().contains("Hilton")));
    }

    @Test
    @DisplayName("Search hotels by city - Success")
    void shouldReturnHotelsWhenSearchingByCity() {
        // Act
        List<HotelPreviewDto> result = searchService.search(null, null, "Minsk", null, null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(hotel -> hotel.name().contains("Minsk")));
    }

    @Test
    @DisplayName("Search hotels by country - Success")
    void shouldReturnHotelsWhenSearchingByCountry() {
        // Act
        List<HotelPreviewDto> result = searchService.search(null, null, null, "Belarus", null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().allMatch(hotel ->
                hotel.name().equals("DoubleTree by Hilton Minsk") ||
                        hotel.name().equals("The Ritz-Carlton Minsk")));
    }

    @Test
    @DisplayName("Search hotels by amenities - Success")
    void shouldReturnHotelsWhenSearchingByAmenities() {
        // Act
        List<HotelPreviewDto> result = searchService.search(null, null, null, null, Set.of("Free WiFi"));

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.stream().allMatch(hotel ->
                hotel.name().equals("DoubleTree by Hilton Minsk") ||
                        hotel.name().equals("The Ritz-Carlton Minsk") ||
                        hotel.name().equals("Hotel Moscow")));
    }

    @Test
    @DisplayName("Search hotels by multiple parameters - Success")
    void shouldReturnHotelsWhenSearchingByMultipleParameters() {
        // Act
        List<HotelPreviewDto> result = searchService.search("Hilton", "Hilton", "Minsk", "Belarus", null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("DoubleTree by Hilton Minsk", result.get(0).name());
    }

    @Test
    @DisplayName("Search hotels with no matching results - Empty list")
    void shouldReturnEmptyListWhenNoMatchingHotelsFound() {
        // Act
        List<HotelPreviewDto> result = searchService.search(null, null, "Paris", null, null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Search all hotels with no parameters - All hotels returned")
    void shouldReturnAllHotelsWhenNoParametersProvided() {
        // Act
        List<HotelPreviewDto> result = searchService.search(null, null, null, null, null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Search hotels with partial name match - Success")
    void shouldReturnHotelsWhenSearchingByPartialName() {
        // Act
        List<HotelPreviewDto> result = searchService.search("ritz", null, null, null, null);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("The Ritz-Carlton Minsk", result.get(0).name());
    }

}