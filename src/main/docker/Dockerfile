FROM openjdk:11-jre-slim

ARG JAR_FILE
ARG ARTIFACT_ID

RUN echo $(ls)
RUN echo $(pwd)

ADD maven/${JAR_FILE} ${REPO_NAME}.jar

RUN echo $(ls)

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]