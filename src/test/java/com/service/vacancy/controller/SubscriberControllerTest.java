package com.service.vacancy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.vacancy.dto.SubscriberList;
import com.service.vacancy.entity.Subscriber;
import com.service.vacancy.repository.SubscriberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriberController.class)
class SubscriberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriberRepository subscriberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAddSubscriber() throws Exception {
        Subscriber sub = new Subscriber();
        sub.setEmail("ivan@example.com");
        sub.setFullName("Иван Иванов");
        sub.setCity("Москва");
        sub.setInterestedPosition("Java Developer");

        SubscriberList subscriberList = new SubscriberList();
        subscriberList.setSubscribers(Arrays.asList(sub));

        mockMvc.perform(post("/subscriber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subscriberList)))
                .andExpect(status().isOk());
    }
}