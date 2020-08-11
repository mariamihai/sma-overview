FROM openjdk:11-jre-slim

ARG ARTIFACT_ID
ARG VERSION

ADD target/${ARTIFACT_ID}.jar ${ARTIFACT_ID}.jar
#ADD target/${ARTIFACT_ID}-${VERSION}.jar ${ARTIFACT_ID}.jar

RUN echo $(ls)

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]