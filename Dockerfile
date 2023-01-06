FROM debian:stretch-slim
RUN apt-get update && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*
RUN apt-get install -y zlib1g-dev && apt-get install -y build-essential && apt-get install libfreetype6 -y
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
