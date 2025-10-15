package com.service.vacancy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.vacancy.entity.Subscriber;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriberList {
    private List<Subscriber> subscribers;
}
