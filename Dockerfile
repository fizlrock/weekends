
FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests

FROM alpine:3.10.3 as packager
RUN apk --no-cache add openjdk11-jdk openjdk11-jmods
ENV JAVA_MINIMAL="/opt/java-minimal"

RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
java.base,java.logging,java.sql,java.naming,java.management,java.instrument,java.desktop,java.security.jgss,jdk.unsupported \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --output "$JAVA_MINIMAL"



FROM alpine:3.10.3
LABEL maintainer="fizlrock nirku0soft@yandex.ru"
ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"
COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"
COPY --from=builder /src/target/*.jar app.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","/app.jar"]
