FROM debian:bookworm-20221219-slim
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 0E98404D386FA1D9 && apt update && apt-get install -y build-essential && apt-get install -y zip && apt-get install wget -y
RUN wget https://download.savannah.gnu.org/releases/freetype/freetype-2.12.1.tar.gz && tar xvfz freetype-2.12.1.tar.gz && cd freetype-2.12.1 && ./configure --prefix=/usr/local/freetype/2_12_1 --enable-freetype-config && make && make install
ENV LD_LIBRARY_PATH=/usr/local/freetype/2_12_1/lib
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
