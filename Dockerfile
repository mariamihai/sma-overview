FROM openjdk:11-jre-slim
ADD ./target/${project.build.finalName}.jar ${project.build.finalName}.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]