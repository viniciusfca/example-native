FROM alpine:3.17.0
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY exemplo-native /app
CMD ["./exemplo-native"]
