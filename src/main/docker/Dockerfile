FROM openjdk:11-jre-slim

RUN echo ls

ARG JAR_FILE
ARG ARTIFACT_ID

ADD ./target/${JAR_FILE} ${ARTIFACT_ID}.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]