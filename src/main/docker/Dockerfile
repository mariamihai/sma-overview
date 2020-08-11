FROM adoptopenjdk/openjdk11

ARG JAR_FILE
ARG ARTIFACT_ID

RUN echo $(ls)
RUN echo $(pwd)
#
#ADD maven/${JAR_FILE} ${REPO_NAME}.jar
#
#RUN echo $(ls)
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]

RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/licensingservice
ADD ${JAR_FILE} /usr/local/licensingservice/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh