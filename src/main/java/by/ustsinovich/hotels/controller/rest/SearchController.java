package by.ustsinovich.hotels.controller.rest;

import by.ustsinovich.hotels.dto.hotel.HotelPreviewDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;

@Validated
@RequestMapping("/property-view/search")
@Tag(name = "Hotel Search", description = "Operations for searching hotels")
public interface SearchController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(
            summary = "Search hotels",
            description = "Search for hotels based on various criteria including " +
                    "name, brand, city, country, and amenities"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotels found successfully",
                    content = @Content(schema = @Schema(implementation = HotelPreviewDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    List<HotelPreviewDto> search(
            @Parameter(description = "Hotel name (partial match supported)")
            @RequestParam(required = false) String name,

            @Parameter(description = "Hotel brand (exact match)")
            @RequestParam(required = false) String brand,

            @Parameter(description = "City name (exact match)")
            @RequestParam(required = false) String city,

            @Parameter(description = "Country name (exact match)")
            @RequestParam(required = false) String country,

            @Parameter(description = "Set of amenities (hotels with at least one matching amenity will be returned)")
            @RequestParam(required = false) Set<String> amenities
    );

}