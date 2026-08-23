FROM eclipse-temurin:26-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# δំបញ្ជាក់និងផ្តល់សិទ្ធិ execution ដល់ mvnw
RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/Cafe_Demo-0.0.1-SNAPSHOT.jar"]