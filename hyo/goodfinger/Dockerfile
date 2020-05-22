FROM openjdk:13-alpine
MAINTAINER Hyojin Lee <hyovel00@gmail.com>

#RUN apt-get update

VOLUME /tmp

EXPOSE 8080

#ARG JAR_FILE=gradle/wrapper/gradle-wrapper.jar
#ADD ${JAR_FILE} goodfinger-hyo.jar

COPY ./build/libs/* ./goodfinger-hyo.jar


ENTRYPOINT ["java","-jar","goodfinger-hyo.jar"]
