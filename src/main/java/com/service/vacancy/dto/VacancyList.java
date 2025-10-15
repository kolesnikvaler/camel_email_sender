package com.service.vacancy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.vacancy.entity.Vacancy;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyList {
    private List<Vacancy> vacancies;
}
