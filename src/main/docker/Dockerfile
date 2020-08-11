FROM openjdk:11-jre-slim

RUN echo $JAVA_HOME

ARG JAR_FILE
ARG ARTIFACT_ID

ADD ./target/${JAR_FILE} ${ARTIFACT_ID}.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]