## 📘 Book Service — CRUD + Фильтрация + Аутентификация

### 🇷🇺 Описание

Приложение для управления книгами, разработанное на Spring Boot. Поддерживает CRUD-операции, фильтрацию книг по названию, году и бренду, а также аутентификацию пользователей (in-memory).

---

### ⚙️ Функционал

- Просмотр списка книг
- Поиск и фильтрация по:
  - Названию (`title`)
  - Году публикации (`publicationYear`)
  - Бренду (`brand`)
- Создание, обновление и удаление книги
- REST API (`/api/books`)
- HTML-интерфейс с использованием Thymeleaf и Bootstrap (`/books`)
- Форма авторизации (`/login`)
- Защита с помощью Spring Security (in-memory)

---

### 🛠️ Технологии

- Java 17+
- Spring Boot 3+
- Spring Security
- Spring Data JPA
- H2 Database (встроенная)
- Thymeleaf + Bootstrap
- Lombok
- JUnit + Mockito

---

### ▶️ Как запустить

1. Склонировать репозиторий:

   ```bash
   git clone https://github.com/your-username/book_service.git
   cd book_service
   ```

2. Собрать и запустить:

   ```bash
   ./mvnw spring-boot:run
   ```

3. Перейти в браузер:

   - UI: [http://localhost:8080/books](http://localhost:8080/books)
   - REST: [http://localhost:8080/api/books](http://localhost:8080/api/books)
   - H2-консоль: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

### 🔐 Логин и пароль

- Логин: `admin`  
- Пароль: `admin`

---

### ✅ Тестирование

```bash
./mvnw test
```

---

## 📘 Book Service — CRUD + Filtering + Authentication

### 🇬🇧 Description

Book management application built with Spring Boot. Supports full CRUD operations, filtering by title, publication year, and brand, and user login with in-memory authentication.

---

### ⚙️ Features

- View book list
- Filter by:
  - Title (`title`)
  - Publication year (`publicationYear`)
  - Brand (`brand`)
- Create, update, and delete books
- REST API (`/api/books`)
- HTML interface with Thymeleaf and Bootstrap (`/books`)
- Login form (`/login`)
- Spring Security authentication (in-memory)

---

### 🛠️ Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Security
- Spring Data JPA
- H2 Database (in-memory)
- Thymeleaf + Bootstrap
- Lombok
- JUnit + Mockito

---

### ▶️ How to Run

1. Clone the repo:

   ```bash
   git clone https://github.com/your-username/book_service.git
   cd book_service
   ```

2. Build and run:

   ```bash
   ./mvnw spring-boot:run
   ```

3. Open in browser:

   - UI: [http://localhost:8080/books](http://localhost:8080/books)
   - REST: [http://localhost:8080/api/books](http://localhost:8080/api/books)
   - H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

### 🔐 Login Credentials

- Username: `admin`  
- Password: `admin`

---

### ✅ Testing

```bash
./mvnw test
```