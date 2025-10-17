package com.service.vacancy.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@CamelSpringBootTest
@SpringBootTest
@ActiveProfiles("test")
class NotificationRouteTest {

    @Autowired
    private CamelContext camelContext;

    @MockBean
    private NotificationProcessor notificationProcessor;

    @EndpointInject("direct:start")
    private ProducerTemplate directStart;

    @BeforeEach
    void adviceRoute() throws Exception {
        // Stop the route if running to allow advice
        camelContext.getRouteController().stopRoute("notification-route");
        AdviceWith.adviceWith(camelContext, "notification-route", a -> a.replaceFromWith("direct:start"));
        camelContext.getRouteController().startRoute("notification-route");
    }

    @Test
    void shouldInvokeProcessorWhenMessageArrives() {
        directStart.sendBody("trigger");
        verify(notificationProcessor, times(1)).process(Mockito.any());
    }
}


