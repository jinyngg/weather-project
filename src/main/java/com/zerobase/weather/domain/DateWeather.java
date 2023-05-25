package com.zerobase.weather.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "date_weather")
public class DateWeather {

    @Id
    private LocalDate date;
    private String weather;
    private String icon;
    private Double temperature;

}
