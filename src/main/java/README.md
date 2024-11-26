
# Trello Clone

A simplified Trello-like application built using **Spring Boot** and **MongoDB**, designed for task tracking and management. This project allows users to create, update, and manage tasks while providing insights into task completion times, task sizing, and resource allocation.

## Features

- **Task Management**: Create, update, delete, and view tasks in different states (`Todo`, `Doing`, `Done`).
- **User Assignment**: Assign tasks to specific users.
- **Comments**: Add and manage comments for each task.
- **Task Analysis**:
  - Monitor task start times relative to creation.
  - Track resource sufficiency and task completion efficiency.
- **Board View**: Display tasks grouped by their current states.

## Project Design

This project adheres to clean, scalable design principles and leverages the following design patterns:

1. **State Pattern**: Manages task states (`Todo`, `Doing`, `Done`) dynamically.
2. **Memento Pattern**: Tracks and allows rollback of task state changes to ensure task history integrity.
3. **Singleton Pattern**: Ensures a single instance of key services, such as the database connection handler.

## Technology Stack

- **Backend**: Spring Boot (Java)
- **Database**: MongoDB (NoSQL)
- **Tools**: Postman for API testing
- **Build Tool**: Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- MongoDB installed locally or a MongoDB cluster set up
- Maven for dependency management

### Setup Instructions

1. Clone the repository:
   ```bash 
   git clone https://github.com/your-username/trello-clone.git
   cd trello-clone
   ```

2. Configure MongoDB:
   - Set up a local MongoDB instance or use an online MongoDB cluster.
   - Update the `application.properties` file in the `src/main/resources` directory with your MongoDB credentials:
     ```properties
     spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster.mongodb.net/<database>?retryWrites=true&w=majority
     ```

3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
   - Use Postman or your browser to interact with the API endpoints at `http://localhost:8080`.

### Sample Endpoints

- **Create Task**:
  ```http
  POST /tasks
  {
    "taskId": "1",
    "state": "Todo",
    "assignedTo": "John",
    "description": "Implement user authentication",
    "comments": []
  }
  ```

- **View Tasks**:
  ```http
  GET /tasks
  ```

- **Update Task State**:
  ```http
  PUT /tasks/{taskId}/state
  {
    "state": "Doing"
  }
  ```

## File Structure

```
trello-clone/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.trello.clone/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   ├── resources/
│   │   ├── application.properties
├── pom.xml
├── README.md
```

## Future Enhancements

- Implement user authentication for secure task management.
- Introduce a web-based UI using React or Angular for better user interaction.
- Add reporting and analytics to visualize task completion metrics.
