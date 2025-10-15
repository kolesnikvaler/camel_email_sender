package com.service.vacancy.repository;

import com.service.vacancy.entity.Subscriber;
import com.service.vacancy.entity.Vacancy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryTest {

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Test
    void shouldSaveAndFindVacancy() {
        Vacancy v = new Vacancy();
        v.setName("Test Vacancy");
        v.setPosition("QA Engineer");
        v.setCity("Санкт-Петербург");
        v.setSalaryLevel(100000);
        v.setDescription("Manual testing");
        v.setRequiredExperience("1 год");
        v.setCreatedAt(LocalDateTime.now());

        vacancyRepository.save(v);

        List<Vacancy> found = vacancyRepository.findByFilters(null, "QA Engineer", "Санкт-Петербург");
        assertThat(found).hasSize(1);
        assertThat(found.get(0).getPosition()).isEqualTo("QA Engineer");
    }

    @Test
    void shouldFindSubscribersByPositionAndCity() {
        Subscriber s = new Subscriber();
        s.setEmail("test@example.com");
        s.setFullName("Анна Петрова");
        s.setCity("Санкт-Петербург");
        s.setInterestedPosition("QA Engineer");

        subscriberRepository.save(s);

        List<Subscriber> matches = subscriberRepository.findByInterestedPositionAndCity("QA Engineer", "Санкт-Петербург");
        assertThat(matches).hasSize(1);
        assertThat(matches.get(0).getEmail()).isEqualTo("test@example.com");
    }
}
