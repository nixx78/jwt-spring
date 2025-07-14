# This Proof of Concept (POC) project demonstrates the use of JWT (JSON Web Token)

This Proof of Concept (POC) project demonstrates the use of JWT (JSON Web Token) for authentication and security in a Java Spring application. The key principles of JWT used in the project are:

* Stateless Authentication:
JWT enables stateless authentication, meaning the server does not need to store session information. All necessary user data is encoded within the token itself.

* Token Structure:
A JWT consists of three parts:

* Header – defines the type of token and the signing algorithm (e.g., HS256).
* Payload – contains claims or information about the user (e.g., username, roles).
* Signature – used to verify the token’s integrity and authenticity.

## Token Generation:
After a successful login, the server generates a JWT containing user-related claims and signs it with a secret key.

## Token Transmission:
The client receives the token and sends it in the Authorization header of subsequent HTTP requests using the format:
Authorization: Bearer <token>

## Token Validation:
For each protected request, the server verifies the token’s signature and extracts the claims. If the token is valid, access is granted.

## Security Benefits:
JWT helps in building secure, scalable applications by reducing server-side state management and ensuring that client requests can be authenticated easily.

# Application execution

## Obtaining a JWT token
http://localhost:8080/auth/login?username=user_admin&password=1

## Command to send GET request to server:
curl -i -X GET http://localhost:080/hello -H "Authorization: Bearer <TOKEN>"

curl -i -X GET http://localhost:8080/hello -H "Authorization: Bearer eyJDdXN0b21IZWFkZXIiOiJDdXN0b21IZWFkZXIuVmFsdWUiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyX2FkbWluIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE3NTI0NzY3NDAsImV4cCI6MTc1MjQ4MDM0MH0.xCyAtoxQGS0Dt2SH3tB281uqfQAkBJBVK85BBVtwAws"
