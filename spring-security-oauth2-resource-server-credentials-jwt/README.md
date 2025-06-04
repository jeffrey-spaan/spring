# Spring Security OAuth2 Resource Server with Credential Management and JWT Token Management

This project demonstrates how to implement a Spring Security OAuth2 Resource Server with credential management and JWT token management. It includes features for managing client credentials, generating JWT tokens, and securing REST endpoints.

<b>Author:</b> <a href="https://github.com/jeffrey-spaan" target="_blank">Jeffrey Spaan</a><br>
<b>Created:</b> 2025-06-04<br>
<b>Last updated:</b> 2025-06-04

[![](https://img.shields.io/badge/Spring%20Boot-8A2BE2)]() [![](https://img.shields.io/badge/release-May%2022,%202025-blue)]() [![](https://img.shields.io/badge/version-3.5.0-blue)]()

## 1. Why Oauth2 Resource Server with Credential Management and JWT Token Management Matters?
OAuth2 Resource Server with credential management and JWT token management is crucial for modern applications that require secure access to resources. It allows for:
- **Secure Resource Access**: Ensures that only authorized clients can access protected resources.
- **Credential Management**: Provides a way to manage client credentials securely, reducing the risk of unauthorized access.
- **JWT Token Management**: Facilitates stateless authentication, allowing for efficient and scalable resource access without server-side session management.
- **Scalability**: Supports distributed systems by allowing multiple resource servers to validate tokens without needing to share session state.
- **Interoperability**: Enables integration with various clients and services, enhancing the flexibility of the application architecture.

## 2. How to Use This Project

### 2.1 Prerequisites
- [Git](https://git-scm.com/downloads)
- [Java 21 or higher](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- An IDE (e.g., [IntelliJ IDEA](https://www.jetbrains.com/idea/download), Eclipse)
- [Postman](https://www.postman.com/downloads/) or any API testing tool

### 2.2 Clone, build and run the project

To clone the repository, run the following command in your terminal:
```bash
git clone https://github.com/jeffrey-spaan/spring-security-oauth2-resource-server-credentials-jwt.git
```
After cloning the repository, navigate to the project `src\main\resources\certs` directory and execute the commands as per the `README.md` file in this directory.

Note: before continuing, ensure you have Docker Desktop running, as the project uses a PostgreSQL database container.<br />
Then, navigate to the project directory and build the project using Maven:
```bash
cd spring-security-oauth2-resource-server-credentials-jwt
```

```bash
mvn clean install
```

To run the application, you can use the following command:
```bash
mvn spring-boot:run
```

// here a overview of how jwt works, how it is created and how it is managed
## 3. Spring Security OAuth2 Resource Server with JWT Token Management In Action

- To test the <b>REST endpoints</b>, a tool like <b>Postman</b> can be used to send <b>HTTP GET requests</b>.
- A Postman collection is added within the repository `src/main/resources/postman/collection-to-import.json`
- When making requests, the `Accept-Language` header should be set to specify the desired language.
- You can use <b>language codes</b> like `en` for English, `de` for German, or `nl` for Dutch.
- Setting the `Accept-Language` header informs the server about the <b>preferred language for the response</b>, allowing the application to return <b>localized messages</b> accordingly.

**NOTE:** Before making requests, you need to get a JWT token by sending a POST request to the `/auth/login` endpoint with the appropriate client credentials. The response `Authorization` header will include the JWT token, which can then be used to access protected resources.

[![](https://img.shields.io/badge/POST-yellow)]()<br/>
<small>Endpoint:</small> `http://localhost:8081/api/v1/auth/login`<br/>
<small>Body:</small>
```json
{
  "emailOrUsername": "admin",
  "password": "Password123#"
}
```

![01-postman-post-auth-login](https://github.com/jeffrey-spaan/spring/blob/main/spring-security-oauth2-resource-server-credentials-jwt/images/01-postman-post-auth-login.jpg)
<br><br>

Now you can use the JWT token to access protected resources. For example, to get a list of all users, send a GET request to the `/api/v1/users` endpoint with the JWT token in the `Authorization` header.

[![](https://img.shields.io/badge/GET-green)]()<br/>
<small>Endpoint:</small> `http://localhost:8081/api/v1/users`<br/>
<small>Authorization:</small> `your_jwt_token_here`<br/>

![02-postman-get-all-users](https://github.com/jeffrey-spaan/spring/blob/main/spring-security-oauth2-resource-server-credentials-jwt/images/02-postman-get-all-users.jpg)
<br>

## Let's Stay Connected

If you have any questions in regard to this repository and/or documentation, please do reach out.

Remember to:
- <b>Star</b> the [repository](https://github.com/jeffrey-spaan/spring)
- [Follow me](https://github.com/jeffrey-spaan) for more interesting repositories!