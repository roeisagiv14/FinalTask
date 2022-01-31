FROM adoptopenjdk/openjdk11
ARG JARFILE
COPY ${JARFILE} app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]