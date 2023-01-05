FROM openjdk:17.0.1-jdk-slim
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY target/exemplo-rabbit /app
CMD ["./exemplo-native"]
