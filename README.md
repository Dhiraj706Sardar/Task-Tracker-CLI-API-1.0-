# Task Tracker CLI (Spring Boot)

A production-grade Spring Boot CLI-style task tracking app with Swagger, Docker, and JSON file storage.

## Features

- ✅ Add, Update, Delete tasks
- 🚀 Mark as To-Do, In-Progress, or Done
- 📁 JSON-based file storage
- 🧪 Unit tested with JUnit & Mockito
- 🐳 Dockerized
- 🌐 Swagger UI for API testing

## Getting Started

```bash
git clone [<repo-url>](https://github.com/Dhiraj706Sardar/Task-Tracker-CLI-API-1.0-.git)
cd TaskTrackerCLI
mvn clean install
java -jar target/TaskTrackerCLI-0.0.1-SNAPSHOT.jar


### Testing using curl
#### Add task
curl -X POST http://localhost:8080/task/add -H "Content-Type: application/json" -d '{"description":"Buy milk"}'

#### Update task
curl -X PUT http://localhost:8080/task/update/{id} -H "Content-Type: application/json" -d '{"description":"Buy milk and eggs"}'

#### Mark as done
curl -X PUT http://localhost:8080/task/mark-done/{id}

#### List done tasks
curl http://localhost:8080/task/list/done
