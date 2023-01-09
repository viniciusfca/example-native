FROM debian:stretch-slim
RUN apt-get update && apt-get install -y zlib1g-dev && apt-get install -y build-essential && apt-get install -y curl && apt-get install -y zip && apt-get install wget -y && apt-get install libfreetype6 -y
RUN wget https://download.savannah.gnu.org/releases/freetype/freetype-2.12.1.tar.gz && tar xvfz freetype-2.12.1.tar.gz && cd freetype-2.12.1 && ./configure --prefix=/usr/local/freetype/2_12_1 --enable-freetype-config && make && make install
RUN ldconfig -p
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]

