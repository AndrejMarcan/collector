FROM openjdk:8-jre-alpine

COPY build/libs/CollectionManager.jar CollectionManager.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "CollectionManager.jar"]