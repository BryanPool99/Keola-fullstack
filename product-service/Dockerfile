# Paso 1: Usa una imagen con Maven y Java 17
FROM maven:3.9.4-eclipse-temurin-17 as builder

# Paso 2: Establece un directorio de trabajo dentro del contenedor
WORKDIR /app

# Paso 3: Copia los archivos de la aplicación al directorio de trabajo
COPY . .

# Paso 4: Compila la aplicación con Maven
RUN mvn clean package -DskipTests

# Paso 5: Correr la aplicación
FROM eclipse-temurin:17-jre

# Paso 6: Establece un directorio de trabajo dentro del contenedor
WORKDIR /app

# Paso 7: Copia el jar compilado
COPY --from=builder /app/target/*.jar app.jar

# Paso 8: Expone el puerto 8011
EXPOSE 8011

# Paso 9: Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
