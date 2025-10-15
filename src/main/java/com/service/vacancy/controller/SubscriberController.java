package com.service.vacancy.controller;

import com.service.vacancy.dto.SubscriberList;
import com.service.vacancy.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriberController {

    private final SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberController(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    /**
     * Добавляет подписчиков на вакансии (по полю interestedPosition)
     * @param subscribers список подписчиков
     */
    @PostMapping("/subscriber")
    public ResponseEntity<Void> addSubscriber(@RequestBody SubscriberList subscribers) {
        subscriberRepository.saveAll(subscribers.getSubscribers());
        return ResponseEntity.ok().build();
    }

}
