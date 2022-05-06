# Run the application as follows:

- Right click on the application class SpringBootSecurityPostgresqlApplication and choose run as java application
- The application will be started in port 8080
- We also need to add some rows into roles table before assigning any role to User.
  Run following SQL insert statements:

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

## To test the endpoints

- open postman

  - to test the signup use URL:http://localhost:8080/api/v1/signup
    provide request body as
    {
    "username": "test",
    "email": "test@gmail.com",
    "password": "12345678",
    "role":["mod","user"]
    }

- To test signin
  use URL http://localhost:8080/api/v1/signin
  request body as

  {
  "username": "test",
  "password": "12345678"
  }
  this will generate jwt at response
  the response is as follows:

  {
  "id": 3,
  "username": "test",
  "email": "test@gmail.com",
  "roles": [
  "ROLE_USER",
  "ROLE_MODERATOR"
  ],
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjUxNzY1Njc3LCJleHAiOjE2NTE4NTIwNzd9.rzA3r8VgdpmWvFNyG7rkzdQj20qlSUC6bPojFz31-vjMpNhkkE4tN4XXUrwmHWtbknq2cNZKkwF0r5VeFObKgA",
  "tokenType": "Bearer"
  }

  - The accesstoken generated can be used to test the todo endpoints

  ## Testing todo endpoints

  - testing post endpoint http://localhost:8080/api/v1/todos
    provide request body as
    {
    "name": "Walk ",
    "description": "1km walk",
    "userId":"3",
    "status":"NOT_STARTED"

}

- get endpoint for todo
  http://localhost:8080/api/v1/todos
  will return list of todos.
