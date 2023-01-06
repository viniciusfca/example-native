FROM openjdk:17.0.1-jdk-slim
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
