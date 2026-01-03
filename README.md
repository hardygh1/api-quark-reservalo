# 📅 Reservalo API - Quarkus Reactive & Clean Architecture

Este proyecto es una **API REST reactiva** desarrollada con **Quarkus**, orientada a la gestión de reservas de servicios profesionales.
Está diseñada para ser **no bloqueante**, altamente concurrente y alineada con **Clean Architecture**.

---

## 🚀 Stack Tecnológico
    
- **Framework:** Quarkus 3.x (Supersonic Subatomic Java)
- **Lenguaje:** Java 21+
- **Reactividad:** Mutiny (Uni / Multi)
- **Persistencia:** Hibernate Reactive + MySQL 8
- **ORM Simplificado:** Panache
- **Mapeo DTO ↔ Entity:** MapStruct
- **Documentación:** SmallRye OpenAPI (Swagger UI)
- **Build Tool:** Maven 3.9+

---

## 🏗️ Arquitectura (Clean Architecture)

El proyecto se encuentra estructurado en capas claramente desacopladas:

### 🔹 Dominio
- Entidades: `Reserva`, `Usuario`, `Servicio`
- Interfaces de repositorio
- Reglas de negocio puras

### 🔹 Aplicación
- Servicios reactivos (`ReservaServiceImpl`)
- Orquestación de casos de uso
- Lógica de negocio sin dependencias técnicas

### 🔹 Infraestructura
- **Persistencia:** Hibernate Reactive + Panache
- **Web:** RESTEasy Reactive (JAX-RS)
- **Mappers:** MapStruct
- **Configuración:** application.properties / application.yml

---

## ▶️ Ejecución del Proyecto

### Requisitos Previos
- Java 21+
- Maven 3.9+
- MySQL 8 o Docker

### Clonar el repositorio
```bash
git clone https://github.com/hardygh1/api-quark-reservalo.git
cd api-quark-reservalo
```

### Ejecutar en modo desarrollo (Live Coding)
```bash
./mvnw quarkus:dev
```

📌 Dev UI disponible en:
```
http://localhost:8080/q/dev/
```

---

## 📦 Empaquetado y Ejecución

### Build estándar
```bash
./mvnw package
```

Ejecutar:
```bash
java -jar target/quarkus-app/quarkus-run.jar
```

### Build como Uber-JAR
```bash
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/*-runner.jar
```

---

## ⚡ Ejecutable Nativo (GraalVM)

```bash
./mvnw package -Dnative
```

O usando contenedor:
```bash
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Ejecutar:
```bash
./target/reservalo-api-runner
```

---

## 📖 Documentación Swagger

Interfaz interactiva para probar los endpoints:

🔗 **URL:**
```
http://localhost:8080/q/swagger-ui/
```

---

## 🧪 Ejemplo de Endpoint

### Crear Reserva
**POST** `/api/v1/reservas`

```json
{
  "estado": "PENDIENTE",
  "cliente_id": 1,
  "detalles": [
    {
      "fechaHoraInicio": "2026-03-10T12:00:00",
      "fechaHoraFin": "2026-03-10T13:00:00",
      "precioFinal": 50.0,
      "servicio_id": 2
    }
  ]
}
```

---

## 📚 Guías Oficiales de Quarkus

- Reactive SQL Clients (MySQL)
- RESTEasy Reactive
- SmallRye OpenAPI
- Hibernate Reactive

🔗 https://quarkus.io/guides

---

## 🧠 Notas Finales

Este proyecto es ideal como:
- Base para microservicios reactivos
- Backend de alta concurrencia
- Referencia de Clean Architecture con Quarkus

---

✍️ **Autor:** Hardy Guizado  
🇵🇪 Perú
