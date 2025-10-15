package com.service.vacancy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subscribers")
@Data
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String fullName;
    private String city;
    private String interestedPosition;
}
