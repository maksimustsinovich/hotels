package by.ustsinovich.hotels.controller.rest;

import by.ustsinovich.hotels.enumeration.HistogramParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Validated
@RequestMapping("/property-view/histogram")
@Tag(name = "Histogram", description = "Operations for getting histogram data about hotels")
public interface HistogramController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{param}")
    @Operation(
            summary = "Get histogram data",
            description = "Retrieves histogram data grouped by the specified parameter"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Histogram data retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "400", description = "Invalid parameter value")
    })
    Map<String, Long> getHistogramByParam(
            @Parameter(description = "Parameter to group by. Valid values: brand, city, country, amenities",
                    example = "brand")
            @NotNull(message = "Param cannot be null")
            @PathVariable HistogramParameter param
    );

}