FROM ubuntu:lunar-20221216
RUN apt-get update && apt-get install libfreetype6 -y && export LD_LIBRARY_PATH=/usr/lib/x86_64-linux-gnu
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
