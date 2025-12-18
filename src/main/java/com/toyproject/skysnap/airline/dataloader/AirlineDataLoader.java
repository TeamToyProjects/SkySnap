package com.toyproject.skysnap.airline.dataloader;

import com.toyproject.skysnap.airline.entitiy.Airline;
import com.toyproject.skysnap.airline.repository.AirlineRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// csv 외부 데이터 동기화
@Component
@RequiredArgsConstructor
public class AirlineDataLoader {

    private final AirlineRepository airlineRepository;

    @PostConstruct   // 스프링이 Bean 생성, 의존성 주입을 끝낸 뒤 딱 한 번 실행되는 메서드. 서버 시작할 때 초기 데이터 적재에 자주 사용함.
    public void load() throws IOException {
        if (airlineRepository.count() > 0) return;  // 데이터 중복을 방지. 이미 데이터가 있으면 적재하지 않음!

        /** 개인 pc에서 데이터로 읽을 때 가능한 코드
        List<String> lines = Files.readAllLines(Path.of("airline.csv"));
         for(String line : lines) {
         String[] tokens = line.split(",");

         String name = tokens[1];
         String iata = tokens[3];
         String country = tokens[6];

         if (iata == null || iata.isBlank()) continue;

         airlineRepository.save(Airline.of(iata,name,country));
         **/

        // classpath(resources)에서 CSV 파일 로드
        ClassPathResource resource =
                new ClassPathResource("data/airline.csv");

        List<String> lines = Files.readAllLines(
                resource.getFile().toPath()
        );

        boolean first = true; // 헤더 스킵용 (선택)

        for(String line : lines) {
            if(first) {
                first = false;
                continue;
            }

            String[] tokens = line.split(",");

            String name = tokens[1];
            String iata = tokens[3];
            String country = tokens[6];

            // 따옴표 제거 + 공백 제거
            iata = iata.replace("\"", "").trim();

            // ❌ 제외 대상
            // 1) 빈 값
            // 2) "-" (Private flight 등)
            // 3) 길이가 2가 아닌 값 (IATA는 무조건 2자리)
            if (iata.isBlank() || "-".equals(iata) || iata.length() != 2) {
                continue;
            }

            // CSV에서 **빈 문자열 코드("")**가 여러 줄 존재해서 오류가 남
            // if (iata == null || iata.isBlank()) continue;

            airlineRepository.save(
                    Airline.of(iata, name, country)
            );
        }
    }
}
