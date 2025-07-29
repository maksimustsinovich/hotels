package by.ustsinovich.hotels.hotels;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;
import by.ustsinovich.hotels.exception.ResourceNotFoundException;
import by.ustsinovich.hotels.service.HotelService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

@DisplayName("Get hotels")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetHotelsTests {

    @Autowired
    private HotelService hotelService;

    @Test
    @DisplayName("Get all hotels when no hotels exist - Empty list")
    void shouldReturnEmptyListWhenNoHotelsExist() {
        // Act
        List<HotelPreviewDto> result = hotelService.getAllHotels();

        // Assert
        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertTrue(result.isEmpty(), "Result should be empty when no hotels exist");
    }

    @Test
    @DisplayName("Get all hotels when hotels exist - Success")
    void shouldReturnAllHotelsWhenHotelsExist() {
        // Arrange
        AddressDto address1 = AddressDto.builder()
                .houseNumber(9L)
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004")
                .build();

        ContactInfoDto contacts1 = ContactInfoDto.builder()
                .phone("+375 17 309-80-00")
                .email("doubletreeminsk.info@hilton.com")
                .build();

        ArrivalTimeDto arrivalTime1 = ArrivalTimeDto.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();

        CreateHotelDto createHotelDto1 = CreateHotelDto.builder()
                .name("DoubleTree by Hilton Minsk")
                .description("Luxury hotel")
                .brand("Hilton")
                .address(address1)
                .contacts(contacts1)
                .arrivalTime(arrivalTime1)
                .build();

        AddressDto address2 = AddressDto.builder()
                .houseNumber(15L)
                .street("Nezavisimosti Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220030")
                .build();

        ContactInfoDto contacts2 = ContactInfoDto.builder()
                .phone("+375 17 289-10-00")
                .email("ritzcarlton@example.com")
                .build();

        ArrivalTimeDto arrivalTime2 = ArrivalTimeDto.builder()
                .checkIn("15:00")
                .checkOut("11:00")
                .build();

        CreateHotelDto createHotelDto2 = CreateHotelDto.builder()
                .name("The Ritz-Carlton Minsk")
                .description("Luxury hotel")
                .brand("The Ritz-Carlton")
                .address(address2)
                .contacts(contacts2)
                .arrivalTime(arrivalTime2)
                .build();

        hotelService.createHotel(createHotelDto1);
        hotelService.createHotel(createHotelDto2);

        // Act
        List<HotelPreviewDto> result = hotelService.getAllHotels();

        // Assert
        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertEquals(2, result.size(), "Should return 2 hotels");
        Assertions.assertEquals("DoubleTree by Hilton Minsk", result.get(0).name());
        Assertions.assertEquals("The Ritz-Carlton Minsk", result.get(1).name());
    }

    @Test
    @DisplayName("Get hotel by valid ID - Success")
    void shouldReturnHotelWhenValidIdProvided() {
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
                .description("Luxury hotel")
                .brand("Hilton")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        HotelPreviewDto createdHotel = hotelService.createHotel(createHotelDto);
        Long hotelId = createdHotel.id();

        // Act
        HotelDto result = hotelService.getHotelById(hotelId);

        // Assert
        Assertions.assertNotNull(result, "Hotel should not be null");
        Assertions.assertEquals(hotelId, result.id(), "Hotel ID should match");
        Assertions.assertEquals("DoubleTree by Hilton Minsk", result.name(), "Hotel name should match");
        Assertions.assertEquals("Luxury hotel", result.description(), "Hotel description should match");
    }

    @Test
    @DisplayName("Get hotel by non-existent ID - Resource not found")
    void shouldThrowExceptionWhenHotelNotFound() {
        // Arrange
        Long nonExistentId = 999L;

        // Act & Assert
        Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> hotelService.getHotelById(nonExistentId),
                "Should throw ResourceNotFoundException for non-existent hotel"
        );
    }

    @Test
    @DisplayName("Get hotel by null ID - Constraint violation")
    void shouldThrowExceptionWhenIdIsNull() {
        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.getHotelById(null),
                "Should throw ConstraintViolationException when ID is null"
        );
    }

    @Test
    @DisplayName("Get hotel by negative ID - Constraint violation")
    void shouldThrowExceptionWhenIdIsNegative() {
        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.getHotelById(-1L),
                "Should throw ConstraintViolationException when ID is negative"
        );
    }

    @Test
    @DisplayName("Get hotel by zero ID - Constraint violation")
    void shouldThrowExceptionWhenIdIsZero() {
        // Act & Assert
        Assertions.assertThrows(
                ConstraintViolationException.class,
                () -> hotelService.getHotelById(0L),
                "Should throw ConstraintViolationException when ID is zero"
        );
    }
}