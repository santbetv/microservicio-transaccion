FROM openjdk:11
COPY /target/app.jar /usr/src/
CMD ["java",  "-jar", "/usr/src/app.jar"]
EXPOSE 8080
