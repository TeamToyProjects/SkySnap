package com.toyproject.skysnap.airline.service;

import com.toyproject.skysnap.airline.dto.AirlineResponse;
import com.toyproject.skysnap.airline.entitiy.Airline;
import com.toyproject.skysnap.airline.mapper.AirlineMapper;
import com.toyproject.skysnap.airline.repository.AirlineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {

    private final AirlineRepository airlineRepository;  // DB 접근을 직접 안하고 레포지토리에게 DB 조회를 맡김.
    private final AirlineMapper airlineMapper;


    public List<AirlineResponse> findAll() { // 엔티티 조회
        List<Airline> airlines = airlineRepository.findAll();

        return airlineMapper.toResponseList(airlines); // DTO 변환은 Mapper에게 위임

    }

    public AirlineResponse findById(Long id) {  // 엔티티 조회
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Airline not found: " + id));

        return airlineMapper.toResponse(airline);  // DTO 변환은 Mapper에게 위임
    }
}


  /** DTO를 통해서 반환한게 아니라 직접 엔티티를 반환한 코드로 엔티티 내부 자체가 노출될 위험이 있어서 DTO로 반환하는걸로 코드 수정
    public List<Airline> findAll() {
        return airlineRepository.findAll();  // 항공사 전체 조회
    }
    public Airline findById(Long id) {
        return airlineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Airline not found: " + id));

    }  **/


  /** Mapper 사용 전: DTO가 직접 변환 책임을 가졌던 코드
    public List<AirlineResponse> findAll() {
        return airlineRepository.findAll()
                .stream()  // Airline 엔티티 리스트를 스트림으로 변환
                .map(AirlineResponse::new)  // 각 Airline 엔티티를 AirlineResponse Dto로 변환
                .toList();  // 다시 List로 수집
    }
    public AirlineResponse findById(Long id){
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Airline not found:" + id));
        return new AirlineResponse(airline);

    }  **/

