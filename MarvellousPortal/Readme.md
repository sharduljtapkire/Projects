## Marvellous Portal - Backend API

This project, named "Marvellous Portal," provides a backend API for managing batch entries. It is built using Spring Boot and utilizes MongoDB as its database.

**Description:**
The Marvellous Portal project offers a RESTful API for performing CRUD (Create, Read, Update, Delete) operations on batch entries. It allows users to manage information related to various batches, including their names and associated fees. The API is designed to be consumed by a frontend application or other clients.

**Technologies Used:**

  * **Spring Boot:** Framework for building the backend application.
  * **MongoDB:** NoSQL database used for storing batch entry data.
  * **Lombok:** Library to reduce boilerplate code (e.g., getters, setters).
  * **Postman:** Used for testing the API endpoints.

**Project Structure:**

  * `com.marvellous.MarvellousPortal.MarvellousPortalApplication`: The main Spring Boot application entry point.
  * `com.marvellous.MarvellousPortal.Entity.BatchEntry`: Defines the data model for a batch entry, mapping to the "BatchDetails" collection in MongoDB. Each `BatchEntry` has an `id` (ObjectId), `name` (String), and `fees` (int).
  * `com.marvellous.MarvellousPortal.Repository.BatchEntryRepository`: An interface extending `MongoRepository`, providing standard MongoDB operations for `BatchEntry` entities.
  * `com.marvellous.MarvellousPortal.Service.BatchEntryService`: Contains the business logic for managing batch entries, acting as an intermediary between the controller and the repository. It includes methods for saving, retrieving all, finding by ID, and deleting by ID.
  * `com.marvellous.MarvellousPortal.controller.BatchEntryController`: The REST controller that handles incoming HTTP requests. It exposes endpoints for:
      * `GET /batches`: Retrieve all batch entries.
      * `POST /batches`: Create a new batch entry.
      * `GET /batches/id/{myid}`: Retrieve a batch entry by its ID.
      * `DELETE /batches/id/{myid}`: Delete a batch entry by its ID.
      * `PUT /batches/id/{myid}`: Update an existing batch entry by its ID.

**How to Run:**

1.  **Prerequisites:**
      * Java Development Kit (JDK) 17 or higher.
      * Apache Maven.
      * MongoDB instance running.
2.  **Clone the repository.**
3.  **Configure MongoDB:** Ensure your MongoDB instance is running and accessible. You might need to configure the MongoDB connection details in `application.properties` (not provided in the given files, but typically located in `src/main/resources`).
4.  **Build the project:**
    ```bash
    mvn clean install
    ```
5.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    Alternatively, you can run the `MarvellousPortalApplication.java` file directly from your IDE.

The application will start on the default Spring Boot port (usually 8080).

**API Endpoints (using Postman):**

  * **Get All Batches:**

      * `GET http://localhost:8080/batches`
      * **Response:** `200 OK` with a list of `BatchEntry` objects, or `404 NOT FOUND` if no batches exist.

  * **Create Batch Entry:**

      * `POST http://localhost:8080/batches`
      * **Headers:** `Content-Type: application/json`
      * **Body (raw, JSON):**
        ```json
        {
            "name": "Full Stack Development",
            "fees": 25000
        }
        ```
      * **Response:** `201 CREATED` with the newly created `BatchEntry` object, or `400 BAD REQUEST` if the request body is invalid.

  * **Get Batch Entry by ID:**

      * `GET http://localhost:8080/batches/id/{objectId}` (Replace `{objectId}` with an actual MongoDB ObjectId)
      * **Response:** `200 OK` with the `BatchEntry` object, or `404 NOT FOUND` if the ID does not exist.

  * **Delete Batch Entry by ID:**

      * `DELETE http://localhost:8080/batches/id/{objectId}` (Replace `{objectId}` with an actual MongoDB ObjectId)
      * **Response:** `204 NO CONTENT`.

  * **Update Batch Entry by ID:**

      * `PUT http://localhost:8080/batches/id/{objectId}` (Replace `{objectId}` with an actual MongoDB ObjectId)
      * **Headers:** `Content-Type: application/json`
      * **Body (raw, JSON):**
        ```json
        {
            "name": "Updated Batch Name",
            "fees": 30000
        }
        ```
      * **Response:** `200 OK` with the updated `BatchEntry` object, or `404 NOT FOUND` if the ID does not exist.