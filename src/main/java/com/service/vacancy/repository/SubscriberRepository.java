package com.service.vacancy.repository;

import com.service.vacancy.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findByInterestedPositionAndCity(String interestedPosition, String city);
}
