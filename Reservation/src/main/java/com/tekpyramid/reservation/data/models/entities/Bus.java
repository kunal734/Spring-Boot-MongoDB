package com.tekpyramid.reservation.data.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bus")
public class Bus {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String busNumber;
    private LocalDate dateOfDeparture;
    private String from;
    private String to;
    private String noOfSeats;
    private Admin admin;
}
