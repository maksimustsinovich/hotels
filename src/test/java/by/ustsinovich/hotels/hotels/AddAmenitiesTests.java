package by.ustsinovich.hotels.hotels;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;
import by.ustsinovich.hotels.service.HotelService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AddAmenitiesTests {

    @Autowired
    private HotelService hotelService;

    @Test
    void shouldAddAmenitiesToExistingHotel() {
        // Arrange
        AddressDto address = AddressDto.builder()
                .houseNumber(9L)
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004")
                .build();

        ContactInfoDto contacts = ContactInfoDto.builder()
                .phone("+375 17 309-80-00")
                .email("doubletreeminsk.info@hilton.com")
                .build();

        ArrivalTimeDto arrivalTime = ArrivalTimeDto.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();

        CreateHotelDto createHotelDto = CreateHotelDto.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("Luxury hotel in Minsk")
                .brand("Hilton")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        HotelPreviewDto createdHotel = hotelService.createHotel(createHotelDto);
        Long hotelId = createdHotel.id();

        Set<String> amenities = Set.of(
                "Free WiFi",
                "Swimming Pool",
                "Fitness Center",
                "Restaurant"
        );

        // Act
        hotelService.addAmenitiesByHotelId(hotelId, amenities);

        // Assert
        Assertions.assertNotNull(hotelId);
    }

    @Test
    void shouldThrowExceptionWhenHotelIdIsNull() {
        // Arrange
        Set<String> amenities = Set.of("Free WiFi", "Parking");

        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.addAmenitiesByHotelId(null, amenities)
        );
    }

    @Test
    void shouldThrowExceptionWhenHotelIdIsNegative() {
        // Arrange
        Set<String> amenities = Set.of("Free WiFi", "Parking");

        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.addAmenitiesByHotelId(-1L, amenities)
        );
    }

    @Test
    void shouldThrowExceptionWhenHotelIdIsZero() {
        // Arrange
        Set<String> amenities = Set.of("Free WiFi", "Parking");

        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.addAmenitiesByHotelId(0L, amenities)
        );
    }

    @Test
    void shouldThrowExceptionWhenAmenitiesIsNull() {
        // Arrange
        AddressDto address = AddressDto.builder()
                .houseNumber(15L)
                .street("Nezavisimosti Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220030")
                .build();

        ContactInfoDto contacts = ContactInfoDto.builder()
                .phone("+375 17 289-10-00")
                .email("ritzcarlton@example.com")
                .build();

        ArrivalTimeDto arrivalTime = ArrivalTimeDto.builder()
                .checkIn("15:00")
                .checkOut("11:00")
                .build();

        CreateHotelDto createHotelDto = CreateHotelDto.builder()
                .name("The Ritz-Carlton Minsk")
                .description("Luxury hotel")
                .brand("The Ritz-Carlton")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        HotelPreviewDto createdHotel = hotelService.createHotel(createHotelDto);
        Long hotelId = createdHotel.id();

        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.addAmenitiesByHotelId(hotelId, null)
        );
    }

    @Test
    void shouldThrowExceptionWhenAmenitiesIsEmpty() {
        // Arrange
        AddressDto address = AddressDto.builder()
                .houseNumber(15L)
                .street("Nezavisimosti Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220030")
                .build();

        ContactInfoDto contacts = ContactInfoDto.builder()
                .phone("+375 17 289-10-00")
                .email("ritzcarlton@example.com")
                .build();

        ArrivalTimeDto arrivalTime = ArrivalTimeDto.builder()
                .checkIn("15:00")
                .checkOut("11:00")
                .build();

        CreateHotelDto createHotelDto = CreateHotelDto.builder()
                .name("The Ritz-Carlton Minsk")
                .description("Luxury hotel")
                .brand("The Ritz-Carlton")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        HotelPreviewDto createdHotel = hotelService.createHotel(createHotelDto);
        Long hotelId = createdHotel.id();
        Set<String> emptyAmenities = Set.of();

        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.addAmenitiesByHotelId(hotelId, emptyAmenities)
        );
    }
}