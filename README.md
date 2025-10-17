## Сервис отправки сообщений о вакансиях на почту mail

### Создайте .env в корне проекта и добавьте переменные окружения:
```env
# Ваша почта и сгенерированный вами внешний ключ
MAIL_FROM=example@mail.ru
MAIL_USERNAME=example@mail.ru
MAIL_PASSWORD=${ваш внешний пароль почты}
```

#### Запустите для shell
.\mvnw spring-boot:run
#### Для bash
./mvnw spring-boot:run

#### Подписка и отправка email
```
Запрос на подписку:
POST http://localhost:8080/subscriber
Json:
{
  "subscribers": [
    {
      "email": "example@mail.ru",
      "fullName": "Иван Иванов",
      "city": "Москва",
      "interestedPosition": "Java Developer"
    }
  ]
}

Создаём вакансию:
POST http://localhost:8080/vacancy
Json:
{
  "vacancies": [
    {
      "name": "Java Developer",
      "description": "Разработка backend",
      "position": "Java Developer",
      "salaryLevel": 150000,
      "requiredExperience": "3 года",
      "city": "Москва"
    }
  ]
}

Ждём меньше 10 секунд email на почту
```