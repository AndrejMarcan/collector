FROM openjdk:latest

ADD build/libs/CollectionManager.jar CollectionManager.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "CollectionManager.jar"]