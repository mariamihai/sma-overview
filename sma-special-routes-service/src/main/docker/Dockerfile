FROM openjdk:11-jre-slim as builder
WORKDIR application

ARG JAR_FILE
ARG ARTIFACT_ID

ADD maven/${JAR_FILE} ${ARTIFACT_ID}.jar

RUN sleep 5

RUN java -Djarmode=layertools -jar ${ARTIFACT_ID}.jar extract

FROM openjdk:11-jre-slim

EXPOSE 8070

WORKDIR application

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]