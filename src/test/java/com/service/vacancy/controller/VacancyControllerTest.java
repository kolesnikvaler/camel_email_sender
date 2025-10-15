package com.service.vacancy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.vacancy.dto.VacancyFilterDto;
import com.service.vacancy.dto.VacancyList;
import com.service.vacancy.entity.Vacancy;
import com.service.vacancy.repository.VacancyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VacancyController.class)
class VacancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacancyRepository vacancyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateVacancy() throws Exception {
        Vacancy vacancy = new Vacancy();
        vacancy.setName("Java Dev");
        vacancy.setPosition("Java Developer");
        vacancy.setCity("Москва");
        vacancy.setSalaryLevel(200000);
        vacancy.setDescription("Backend dev");
        vacancy.setRequiredExperience("3 года");

        VacancyList vacancyList = new VacancyList();
        vacancyList.setVacancies(Collections.singletonList(vacancy));

        mockMvc.perform(post("/vacancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vacancyList)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetVacanciesByFilters() throws Exception {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);
        vacancy.setName("Senior Java");
        vacancy.setPosition("Java Developer");
        vacancy.setCity("Москва");
        vacancy.setSalaryLevel(250000);
        vacancy.setCreatedAt(LocalDateTime.now());

        VacancyFilterDto vacancyFilterDto = new VacancyFilterDto();
        vacancyFilterDto.setName("Java");
        vacancyFilterDto.setPosition("Java Developer");
        vacancyFilterDto.setCity("Москва");

        when(vacancyRepository.findByFilters("Java", "Java Developer", "Москва"))
                .thenReturn(Collections.singletonList(vacancy));

        mockMvc.perform(get("/vacancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vacancyFilterDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Senior Java"));
    }
    }
