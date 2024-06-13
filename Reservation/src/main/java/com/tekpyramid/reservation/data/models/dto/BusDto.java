package com.tekpyramid.reservation.data.models.dto;

import com.tekpyramid.reservation.data.models.entities.Admin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class BusDto {

    @NotBlank(message = "Required")
    private String name;
    @NotBlank(message = "Required")
    private String busNumber;
    @NotBlank(message = "Required")
    private String from;
    @NotBlank(message = "Required")
    private String to;
    @NotBlank(message = "Required")
    private String noOfSeats;
    private Admin admin;
}