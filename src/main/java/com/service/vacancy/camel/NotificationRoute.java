package com.service.vacancy.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Настройка маршрутизатора
 */
@Component
public class NotificationRoute extends RouteBuilder {

    private final NotificationProcessor processor;

    public NotificationRoute(NotificationProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void configure() {
        from("timer://dailyNotifier?period=10000") // каждые 10 секунд
                .routeId("notification-route")
                .log("Запуск рассылки уведомлений...")
                .process(processor);
    }

}
