# trafficon_application_project

This project is a simple Spring Boot-based REST API for managing and displaying current traffic jams. It provides endpoints to create, retrieve, and delete traffic jams, as well as filtering jams by district.

## Features
- **Create a Traffic Jam**: You can create a traffic jam with details such as location, description, and severity.
- **Retrieve All Traffic Jams**: A list of all currently registered traffic jams can be retrieved.
- **Filter Traffic Jams by District**: Traffic jams can be filtered by district.
- **Delete Traffic Jams**: All traffic jams or specific ones by location can be deleted.

## API Endpoints and Example Requests

### Create a Traffic Jam
**POST** `/create`

Example Requests:

POST http://localhost:8080/create
Content-Type: application/json

{
  "name": "John",
  "description": "Accident on Highway with car",
  "location": "A1",
  "severity": "LOW",
  "district": "Salzburg"
}

DELETE http://localhost:8080/deleteAllJams

@location=A2
DELETE http://localhost:8080/deleteJamByLocation/{{location}}

@district=Salzburg
GET http://localhost:8080/getJamsByDistrict/{{district}}


GET http://localhost:8080/getAllJams
