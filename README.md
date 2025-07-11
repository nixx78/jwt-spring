# JWT Spring sandbox project

## Obtaining a JWT token
http://localhost:8080/auth/login?username=user_power&password=1

## Command to send GET request to server:
curl -i -X GET http://localhost:8080/hello -H "Authorization: Bearer <TOKEN>"
