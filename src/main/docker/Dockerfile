FROM anapsix/alpine-java:8
EXPOSE 8080
VOLUME /tmp
ADD eipl.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-server","-Xms128m","-Xmx256m","-XX:+UseG1GC","-XX:+UseStringDeduplication", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]