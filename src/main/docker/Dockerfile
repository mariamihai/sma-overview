#FROM openjdk:11-jre-slim
#
#ARG JAR_FILE
#ARG ARTIFACT_ID
#
#RUN echo $(ls)
#RUN echo $(pwd)
#
#ADD maven/${JAR_FILE} ${ARTIFACT_ID}.jar
#
#RUN sleep 5
#RUN echo $(ls)
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]

FROM openjdk:11-jre-slim as builder
WORKDIR application

ARG JAR_FILE
ARG ARTIFACT_ID

RUN echo $(ls)
RUN echo $(pwd)

ADD maven/${JAR_FILE} ${ARTIFACT_ID}.jar

RUN sleep 5
RUN echo $(ls)

EXPOSE 8080

RUN java -Djarmode=layertools -jar ${ARTIFACT_ID}.jar extract

FROM openjdk:11-jre-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/resources/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]