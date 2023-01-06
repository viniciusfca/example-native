FROM debian:stretch-slim
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY exemple-native /app
CMD ["./exemple-native"]
