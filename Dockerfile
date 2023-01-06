FROM debian:stretch-slim
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY target/example-native /app
CMD ["./example-native"]
