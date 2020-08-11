FROM openjdk:11-jre-slim

ARG JAR_FILE
ARG ARTIFACT_ID

RUN echo ${JAR_FILE}
RUN echo ${ARTIFACT_ID}

ADD target/${project.artifactId}-${project.version}.jar ${project.artifactId}.jar

RUN echo $(ls)

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]