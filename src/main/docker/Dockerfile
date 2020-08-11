FROM openjdk:11-jre-slim

ARG JAR_FILE
ARG ARTIFACT_ID

ARG BUILD_ARG
RUN echo ${BUILD_ARG}

ADD target/${JAR_FILE} ${ARTIFACT_ID}.jar

RUN echo $(ls)

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]