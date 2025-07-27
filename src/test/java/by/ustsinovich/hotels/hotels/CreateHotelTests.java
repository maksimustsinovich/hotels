package by.ustsinovich.hotels.hotels;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import by.ustsinovich.hotels.dto.AddressDto;
import by.ustsinovich.hotels.dto.ArrivalTimeDto;
import by.ustsinovich.hotels.dto.ContactInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateHotelTests {

    @Autowired
    private by.ustsinovich.hotels.service.HotelService hotelService;

    @Test
    void shouldCreateHotelWithValidData() {
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
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the " +
                        "Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        // Act
        HotelPreviewDto result = hotelService.createHotel(createHotelDto);

        // Assert
        Assertions.assertNotNull(result, "Hotel preview should not be null");
        Assertions.assertEquals("DoubleTree by Hilton Minsk", result.name(),
                "Hotel name should match");
        Assertions.assertEquals("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the " +
                        "Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...",
                result.description(), "Hotel description should match");
        Assertions.assertEquals("9 Pobediteley Avenue, Minsk, 220004, Belarus", result.address(),
                "Hotel address should match");
        Assertions.assertEquals("+375 17 309-80-00", result.phone(),
                "Hotel phone should match");
    }

    @Test
    void shouldCreateHotelWithNullDescription() {
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
                .description(null)
                .brand("The Ritz-Carlton")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        // Act
        HotelPreviewDto result = hotelService.createHotel(createHotelDto);

        // Assert
        Assertions.assertNotNull(result, "Hotel preview should not be null");
        Assertions.assertNull(result.description(),
                "Hotel description should be null when not provided");
    }

    @Test
    void shouldCreateHotelWithNullCheckOutTime() {
        // Arrange
        AddressDto address = AddressDto.builder()
                .houseNumber(5L)
                .street("Karl Marx Street")
                .city("Minsk")
                .country("Belarus")
                .postCode("220030")
                .build();

        ContactInfoDto contacts = ContactInfoDto.builder()
                .phone("+375 17 200-50-00")
                .email("marriott@example.com")
                .build();

        ArrivalTimeDto arrivalTime = ArrivalTimeDto.builder()
                .checkIn("13:00")
                .checkOut(null)
                .build();

        CreateHotelDto createHotelDto = CreateHotelDto.builder()
                .name("Minsk Marriott Hotel")
                .description("Modern hotel in the city center")
                .brand("Marriott")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        // Act
        HotelPreviewDto result = hotelService.createHotel(createHotelDto);

        // Assert
        Assertions.assertNotNull(result, "Hotel preview should not be null");
        Assertions.assertNotNull(result.name(), "Hotel name should not be null");
        Assertions.assertEquals("Minsk Marriott Hotel", result.name(),
                "Hotel name should match");
    }

    @Test
    void shouldCreateHotelWithEmptyDescription() {
        // Arrange
        AddressDto address = AddressDto.builder()
                .houseNumber(23L)
                .street("Yakub Kolas Street")
                .city("Minsk")
                .country("Belarus")
                .postCode("220000")
                .build();

        ContactInfoDto contacts = ContactInfoDto.builder()
                .phone("+375 17 229-20-29")
                .email("crowneplaza@example.com")
                .build();

        ArrivalTimeDto arrivalTime = ArrivalTimeDto.builder()
                .checkIn("14:00")
                .checkOut("12:00")
                .build();

        CreateHotelDto createHotelDto = CreateHotelDto.builder()
                .name("Crowne Plaza Minsk")
                .description("")
                .brand("Crowne Plaza")
                .address(address)
                .contacts(contacts)
                .arrivalTime(arrivalTime)
                .build();

        // Act
        HotelPreviewDto result = hotelService.createHotel(createHotelDto);

        // Assert
        Assertions.assertNotNull(result, "Hotel preview should not be null");
        Assertions.assertEquals("", result.description(),
                "Hotel description should be empty string");
    }
}