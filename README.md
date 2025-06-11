# itemService

**itemService** — це мікросервіс для управління товарами у складі масштабованої e-commerce платформи на основі мікросервісної архітектури. Сервіс дозволяє виконувати CRUD-операції над товарами, організовує зберігання інформації про асортимент, ціни, категорії і пов’язаний із іншими сервісами через REST API.

## Основні функції

- Додавання, редагування, перегляд та видалення товарів
- Зберігання детальної інформації про кожен товар (назва, опис, ціна, категорія, кількість на складі тощо)
- Пошук і фільтрація товарів за різними параметрами
- Інтеграція з іншими мікросервісами (userService, orderService, recommendationService) через REST API
- Підтримка роботи з власною базою даних (MySQL)
- Документований REST API для інтеграції з фронтендом та зовнішніми сервісами

## Технології

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Spring Web**
- **Spring Validation**
- **Swagger/OpenAPI** (документація)
- **GitHub Actions** (CI/CD)

## Швидкий старт

1. **Клонувати репозиторій:**
   ```bash
   git clone https://github.com/gr-oleg/itemService.git
   cd itemService
   ```

2. **Налаштувати змінні середовища та підключення до MySQL у `application.properties` або `application.yml`:**
   ```
   spring.datasource.url=jdbc:mysql://<host>:<port>/<db_name>
   spring.datasource.username=<username>
   spring.datasource.password=<password>
   ```

3. **Запустити застосунок:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Відкрити Swagger-документацію:**
   - http://localhost:8080/swagger-ui.html

## Основні REST-ендпоінти

- `GET /item/getAll` — отримати перелік усіх товарів (приклад нижче)
- `GET /item/getById/{id}` — отримати товар за ідентифікатором
- `POST /item/add` — створити новий товар
- `PUT /item/update/{id}` — оновити інформацію про товар
- `DELETE /item/delete/{id}` — видалити товар

### Живий приклад

Сервіс розгорнутий на захищеному EC2:

- [http://16.171.137.58/item/getAll](http://16.171.137.58/item/getAll)
