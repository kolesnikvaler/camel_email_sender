package com.service.vacancy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyFilterDto {
    private String name;
    private String position;
    private String city;
}
