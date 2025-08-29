# 🧩 User Aggregator

This application aggregates users from multiple databases
based on the configuration defined in `application.yml`.
It exposes a REST API to retrieve a unified list of users.

---

## 🚀 Running with Docker Compos

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/user-aggregator.git
   cd user-aggregator
   
2. **Start the databases**
   ```bash
   docker-compose up -d
   ```
   This will spin up:
    - postgres1 (port 5432, database db1)
    - postgres2 (port 5433, database db2)
    - mysql1 (port 3306, database db3)

   Initialization scripts (init-db1.sql, init-db2.sql, init-db3.sql) 
   will create tables and test data.


3. **Run the Spring Boot application**
    ```bash
    mvn spring-boot:run
    ```
##  📦 Data Source Configuration

   Defined in application.yml:
   ```bash  
   data-sources:
     sources:
       - name: data-base-1
         strategy: postgres
         url: jdbc:postgresql://localhost:5432/db1
         table: users
         user: testuser
         password: testpass
         mapping:
           id: user_id
           username: login
           name: first_name
           surname: last_name
   ```
   and any amount of sources can be added.

## 📡 API
- GET /users — returns a unified list of users from all configured source
### 🔍 Sample Response
   ```bash
   [
     {
       "id": "1",
       "username": "alice123",
       "name": "Alice",
       "surname": "Smith"
     },
     {
       "id": "2",
       "username": "bob456",
       "name": "Bob",
       "surname": "Johnson"
     }
   ]
  ```

## 📚 Swagger UI
API documentation is available at:
http://localhost:8080/swagger-ui.html


