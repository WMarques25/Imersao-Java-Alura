FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/linguagens.api-0.0.1-SNAPSHOT.jar linguagens.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar linguagens.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar linguagens.jar
