FROM openjdk:17-jdk-alpine

COPY lib/swiftly.jar swiftly.jar

COPY lib/wait-for-it.ash wait-for-it.ash

RUN chmod +x wait-for-it.ash

EXPOSE 8000

ENTRYPOINT ["./wait-for-it.ash", "db:3306", "--", "java", "-jar", "swiftly.jar"]