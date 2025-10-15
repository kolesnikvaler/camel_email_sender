package com.service.vacancy.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacancies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String position;
    private Integer salaryLevel;
    private String requiredExperience;
    private String city;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
