package com.service.vacancy.camel;

import com.service.vacancy.entity.Subscriber;
import com.service.vacancy.entity.Vacancy;
import com.service.vacancy.repository.SubscriberRepository;
import com.service.vacancy.repository.VacancyRepository;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotificationProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(NotificationProcessor.class);

    private final VacancyRepository vacancyRepository;
    private final SubscriberRepository subscriberRepository;
    private final CamelProperties properties;

    public NotificationProcessor(VacancyRepository vacancyRepository, SubscriberRepository subscriberRepository, CamelProperties properties) {
        this.vacancyRepository = vacancyRepository;
        this.subscriberRepository = subscriberRepository;
        this.properties = properties;
    }

    @Override
    public void process(Exchange exchange) {

        LocalDateTime since = LocalDateTime.now().minusSeconds(10);
        List<Vacancy> newVacancies = vacancyRepository.findByCreatedAtAfter(since);

        for (Vacancy vacancy : newVacancies) {
            List<Subscriber> subscribers = subscriberRepository
                    .findByInterestedPositionAndCity(vacancy.getPosition(), vacancy.getCity());

            for (Subscriber subscriber : subscribers) {

                StringBuilder uri = new StringBuilder(properties.useSsl ? "smtps://" : "smtp://");
                uri.append(properties.smtpHost).append(":").append(properties.smtpPort)
                        .append("?username=").append(properties.smtpUsername)
                        .append("&password=").append(properties.smtpPassword)
                        .append("&from=").append(properties.fromEmail)
                        .append("&to=").append(subscriber.getEmail())
                        .append("&subject=").append("Новая вакансия: ").append(vacancy.getPosition())
                        .append("&contentType=text/plain");

                if (properties.useAuth) uri.append("&mail.smtp.auth=true");
                if (properties.useSsl) uri.append("&mail.smtp.ssl.enable=true");

                try (ProducerTemplate producerTemplate = exchange.getContext().createProducerTemplate()) {
                    log.info("Отправка сообщения на почту {} по вакансии '{}'",
                            subscriber.getEmail(), vacancy.getPosition());
                    producerTemplate.sendBody(uri.toString(), buildEmailBody(subscriber, vacancy));
                } catch (CamelExecutionException | IOException e) { log.error(e.getMessage(), e); }
            }
        }
    }

    private String buildEmailBody(Subscriber subscriber, Vacancy vacancy) {
        return "Здравствуйте, " + subscriber.getFullName() + "!\n" +
                "Информируем вас о новой вакансии на должность «" + vacancy.getPosition() + "».\n" +
                "Наименование: «" + vacancy.getName() + "»\n" +
                "Описание: " + vacancy.getDescription() + "\n" +
                "Уровень зарплаты: " + vacancy.getSalaryLevel() + "\n" +
                "Требуемый опыт работы: " + vacancy.getRequiredExperience() + "\n\n" +
                "С уважением,\nЦифровое Будущее\n" + LocalDate.now();
    }
}
