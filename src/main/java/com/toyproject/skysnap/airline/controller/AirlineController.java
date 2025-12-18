package com.toyproject.skysnap.airline.controller;

import com.toyproject.skysnap.airline.dto.AirlineResponse;
import com.toyproject.skysnap.airline.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  // http 요청을 받고 응답을 돌려주는 컨트롤러임을 알려주는 것. 반환값을 JSON으로 자동 변환해서 응답해줌
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;  // 비즈니스로직을 서비스 클래스에 맡기는것.

    @GetMapping
    public List<AirlineResponse> getAirlines() {  // 항공사 목록을 반환
        return airlineService.findAll();  // 실제 데이터 조회는 서비스에서.
    }

    @GetMapping("/{id}")
    public AirlineResponse getAirline(@PathVariable Long id) {
        return airlineService.findById(id);
    }
}
