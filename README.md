# Trafficon application project

This project is a Spring Boot-based REST API for managing and displaying current traffic jams. It provides endpoints to create, retrieve, and delete traffic jams, as well as filter jams by district. The system uses a PostgreSQL database for persistent storage of traffic jam data and is designed to be flexible and easily extendable, allowing for the addition of new functionality.

## Features
- **Create a Traffic Jam**: You can create a traffic jam with details such as location, description, and severity. The location must be unique, as only one traffic jam can exist at a specific location at any given time. If you attempt to create a new traffic jam at a location where one already exists, the severity and description of the existing jam will be updated instead.
- **Retrieve All Traffic Jams**: A list of all currently registered traffic jams can be retrieved.
- **Filter Traffic Jams by District**: Traffic jams can be filtered by district.
- **Delete Traffic Jams**: All traffic jams or specific ones by location can be deleted.

## API Endpoints and Example Requests

### Create a Traffic Jam
**POST** `/create`

Example Request:
POST http://localhost:8080/create
Content-Type: application/json

{
  "name": "John",
  "description": "Accident on Highway with car",
  "location": "A1",
  "severity": "LOW",
  "district": "Salzburg"
}

### Delete All Traffic Jams
**DELETE** `/deleteAllJams`

Example Request:
DELETE http://localhost:8080/deleteAllJams

### Delete a Traffic Jam by Location
**DELETE** `/deleteJamByLocation/{location}`

Example Request:
@location=A2
DELETE http://localhost:8080/deleteJamByLocation/{{location}}

### Get Traffic Jams by District
**GET** `/getJamsByDistrict/{district}`

Example Request:
@district=Salzburg
GET http://localhost:8080/getJamsByDistrict/{{district}}

### Get All Traffic Jams
**GET** `/getAllJams`

Example Request:
GET http://localhost:8080/getAllJams
