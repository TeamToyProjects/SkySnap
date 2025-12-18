package com.toyproject.skysnap.airline.dto;

import com.toyproject.skysnap.airline.entitiy.Airline;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AirlineResponse {

    private Long id;
    private String code;
    private String name;
    private String country;

    public AirlineResponse(Airline airline) {
        this.id = airline.getId();
        this.code = airline.getCode();
        this.name = airline.getName();
        this.country = airline.getCountry();
    }
}
