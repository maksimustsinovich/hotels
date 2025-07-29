package by.ustsinovich.hotels.controller.rest;

import by.ustsinovich.hotels.dto.hotel.CreateHotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelDto;
import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Validated
@RequestMapping("/property-view/hotels")
@Tag(name = "Hotel Management", description = "Operations for managing hotels")
public interface HotelController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Create a new hotel",
            description = "Creates a new hotel with the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hotel created successfully",
                    content = @Content(schema = @Schema(implementation = HotelPreviewDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    HotelPreviewDto createHotel(
            @Valid
            @RequestBody CreateHotelDto createHotelDto
    );

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{hotelId}/amenities")
    @Operation(
            summary = "Add amenities to hotel",
            description = "Adds a set of amenities to the specified hotel"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Amenities added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    void addAmenitiesByHotelId(
            @Valid
            @NotNull(message = "Hotel ID cannot be null")
            @Positive(message = "Hotel ID must be positive")
            @Parameter(description = "ID of the hotel to add amenities to")
            @PathVariable Long hotelId,

            @Valid
            @NotNull(message = "Amenities cannot be null")
            @NotEmpty(message = "Amenities cannot be empty")
            @Parameter(description = "Set of amenities to add")
            @RequestBody Set<String> amenities
    );

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(
            summary = "Get all hotels",
            description = "Retrieves a list of all hotels with preview information"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotels retrieved successfully",
                    content = @Content(schema = @Schema(implementation = HotelPreviewDto.class)))
    })
    List<HotelPreviewDto> getAllHotels();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{hotelId}")
    @Operation(
            summary = "Get hotel by ID",
            description = "Retrieves detailed information about a specific hotel"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel retrieved successfully",
                    content = @Content(schema = @Schema(implementation = HotelDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid hotel ID"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    HotelDto getHotelById(
            @Valid
            @NotNull(message = "Hotel ID cannot be null")
            @Positive(message = "Hotel ID must be positive")
            @Parameter(description = "ID of the hotel to retrieve")
            @PathVariable Long hotelId
    );

}