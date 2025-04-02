# Labseq API – Quarkus

This is a RESTful API developed in Java using the Quarkus framework.  
It calculates the `labseq(n)` sequence based on the following logic:

n = 0 => 0
n = 1 => 1
n = 2 => 0
n = 3 => 1
n > 3 => labseq(n) = labseq(n - 4) + labseq(n - 3)

It supports very large inputs using an iterative approach with in-memory caching and `BigInteger`.

## 📦 Technologies
- Java 17  
- Quarkus 3.21  
- RESTEasy Reactive  
- OpenAPI + Swagger UI  
- BigInteger (for big number support)  
- In-memory cache (ConcurrentHashMap)  

## ▶️ Run locally (Dev mode)
```bash
./mvnw compile quarkus:dev

Dev UI is available at:
👉 http://localhost:8080/q/dev/

## 📡 REST Endpoints

| Endpoint           | Description                    | Output       |
|--------------------|--------------------------------|--------------|
| `/labseq/{n}`      | Returns value as plain text    | String       |
| `/swagger-ui`      | OpenAPI Documentation (Swagger)| UI Explorer  |

❌ Error Handling
If n < 0, API returns:

HTTP 400 Bad Request  
Body: n must be >= 0

📄 Example usage
curl http://localhost:8080/labseq/10
# Output: 3

⚙️ Production Build
Build JVM version:
./mvnw package
java -Xmx4G -jar target/quarkus-app/quarkus-run.jar
Build Uber-Jar:
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/*-runner.jar

🧊 Native Build (optional)
If using GraalVM:

./mvnw package -Dnative

Using Docker container:
./mvnw package -Dnative -Dquarkus.native.container-build=true

Run:
./target/labseq-api-1.0.0-runner

🌐 Web UI (optional)
This project includes a simple static HTML file that allows you to input a number and query the API via JavaScript.
It is available at:

http://localhost:8080/index.html
🧪 Performance Requirement
This implementation can calculate labseq(100000) in under 10 seconds.

