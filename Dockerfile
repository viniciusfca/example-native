FROM debian:stretch-slim
RUN apt-get update && apt-get install -y zlib1g-dev && apt-get install -y build-essential
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
