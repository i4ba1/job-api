# job-api

- Make sure docker installed
- Open terminal
- cd to the project
- Type sudo docker-compose up --build
- Open new tab in terminal
-./mvnw spring-boot:run

User Rest API
- /api/user/signup

###Request:
{
	"username": "admin",
	"password": "admin123!#",
	"role": ["admin"]
}

###Response:
{
    "message": "User registered successfully!"
}

- /api/user/signIn
### Request:
{
	"username": "admin",
	"password": "admin123!#"
}

### Response:
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYwMzk0NDg4MSwiZXhwIjoxNjA0MDMxMjgxfQ.puHlQZ8Aam2qf6MPF7VgWFwW9PNBYAAmh9dxZ-9RzsQYJLisrUpsXZoTIO453JIh9F9RYMUpcqBr6FsPoWmtFQ",
    "type": "Bearer",
    "id": 1,
    "username": "admin",
    "roles": [
        "ROLE_ADMIN"
    ]
}


Job Rest API
- /api/job/getJobs
###Response:

