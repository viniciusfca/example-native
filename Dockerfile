FROM ubuntu:lunar-20221216
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY exemplo-native /app
CMD ["./exemplo-native"]
