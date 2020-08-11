FROM openjdk:11-jre-slim

RUN echo $JAVA_HOME

ARG JAR_FILE
ARG ARTIFACT_ID

ADD ./target/${JAR_FILE} ${ARTIFACT_ID}.jar

EXPOSE 8888

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]