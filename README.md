# prueba-promad

Microservicio para la gestión de productos 

 Instrucciones de Instalación:
*Requisitos:
- Java 17 o superior
- Maven 3.6+ o Gradle 7+
- Git
* Clonar repositorio
en una ventana bash
git clone 'https://github.com/Marco-A-S-G/prueba-promad.git'
cd order
*Levantar servicio
mvn spring-boot:run
mvn clean package
java -jar target/examen-0.0.1-SNAPSHOT.jar


*Ejecutar pruebas
mvn test -Dtest=ProductServiceImplTest

Endpoints:
POST http://localhost:8080/api/products
{
  "id": 1,
  "name": "Laptop Gaming",
  "price": 1299.99,
  "stock": 50,
  "active": true
}
}
GET http://localhost:8080/api/products/stock/id_buscar
GET http://localhost:8080/api/products/allproducts
H2 Console : 
http://localhost:8080/h2-console

