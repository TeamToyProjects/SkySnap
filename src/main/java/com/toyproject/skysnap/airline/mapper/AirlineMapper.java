package com.toyproject.skysnap.airline.mapper;

import com.toyproject.skysnap.airline.dto.AirlineResponse;
import com.toyproject.skysnap.airline.entitiy.Airline;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirlineMapper {

    // Airline 엔티티 -> AirlineResponse DTO 변환
    public AirlineResponse toResponse(Airline airline) {
        return new AirlineResponse(
                airline.getId(),
                airline.getCode(),
                airline.getName(),
                airline.getCountry()
        );
    }

    // Airline 엔티티 리스트 -> AirlineResponse DTO 리스트 변환
    public List<AirlineResponse> toResponseList(List<Airline> airlines) {
        return airlines.stream()
                .map(this::toResponse)
                .toList();
    }
}
