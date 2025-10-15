package com.service.vacancy.controller;

import com.service.vacancy.dto.VacancyFilterDto;
import com.service.vacancy.dto.VacancyList;
import com.service.vacancy.entity.Vacancy;
import com.service.vacancy.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VacancyController {

    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyController(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @PostMapping("/vacancy")
    public ResponseEntity<Void> createVacancy(@RequestBody VacancyList vacancyList) {
        vacancyRepository.saveAll(vacancyList.getVacancies());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vacancy")
    public List<Vacancy> getVacancies(@RequestBody VacancyFilterDto filter) {
        return vacancyRepository.findByFilters(filter.getName(), filter.getPosition(), filter.getCity());
    }
}
