# Educational project Bookstore
## Author: Olga Balash
## Project Description
A web application for bookstore management with minimal UI, implementing:
- Three-tier architecture (WEB-Service-DAO)
- MVC pattern via command routing
- Clear separation of concerns

## Technologies
| Component       | Technology       | Port  |
|-----------------|------------------|-------|
| Language        | Java 21          | -     |
| Database        | PostgreSQL       | 5432  |
| Web Server      | Apache Tomcat 10 | 8080  |
| Build Tool      | Maven            | -     |
| Logging         | Log4j 2.x        | -     |

## Features
### Core Pages
- Home page
- Error page
- Book catalog
- User list
- Entity detail pages

### Routing
| Command        | URL                  | Handler Class   |
|----------------|----------------------|-----------------|
| Home           | `/?command=home`     | `HomeCommand`   |
| Books          | `/?command=books`    | `BooksCommand`  |
| Users          | `/?command=users`    | `UsersCommand`  |
  

## Architecture Layers
1. **WEB Layer**
   - Servlet, controllers
   - JSP views (deprecated, using HTML)
   - Error handlers

2. **Service Layer**
   - Business logic
   - Transaction management
   - DTO transformations

3. **Persistence Layer**
   - DAO pattern implementation
   - JDBC connections
   - Entity models

## Installation Guide
**1. Clone repository:**
`git clone https://github.com/BalashOlga/bookstore_balash`

**2. Database setup.**
Create database using parameters from:
`src/main/resources/application.properties`

**3. Build and Deployment Commands for Windows:**
####  Run in CMD: 
`set TOMCAT_HOME=C:\path\to\tomcat`  # Update with your actual Tomcat installation path

cd \path\to\bookstore_balash(this project)

`mvn clean package` 

The application is configured to deploy the generated bookstore-1.0.war file directly to: ${env.TOMCAT_HOME}/webapps/

#### Access in Browser:
`http://localhost:8080/bookstore-1.0/controller?command=home`
