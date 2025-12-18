package com.toyproject.skysnap.airline.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airline")
@Getter
@NoArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, unique = true)
    private String code; // ITAT(KE, OZ) 항공사 코드

    @Column(nullable = false)
    private String name; // 항공사 이름

    private String country;

    protected Airline(String code, String name, String country) {   // 생성자 직접 호출을 막기 위해 사용.
        this.code = code;
        this.name = name;
        this.country = country;
    }

    public static Airline of(String code, String name, String country) {
        return new Airline(code, name, country);
    }

}
