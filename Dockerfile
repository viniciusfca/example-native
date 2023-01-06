FROM debian:stretch-slim
RUN apt-get update && apt-get install libfreetype6 -y
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
