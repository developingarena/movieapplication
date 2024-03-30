FROM openjdk:21

EXPOSE 8060

ADD target/spring-boot-movie-app.jar spring-boot-movie-app.jar

ENTRYPOINT ["java", "-jar", "spring-boot-movie-app.jar"]