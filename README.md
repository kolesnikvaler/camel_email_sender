## Сервис отправки сообщений о вакансиях на почту mail

### Создайте .env в корне проекта и добавьте переменные окружения:
```env
MAIL_FROM=example@mail.ru
MAIL_USERNAME=example@mail.ru
MAIL_PASSWORD=${ваш внешний пароль почты}
```

#### Запустите для shell
.\mvnw spring-boot:run
#### Для bash
./mvnw spring-boot:run