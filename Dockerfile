FROM debian:rc-buggy-20221219
RUN echo 'deb http://ftp.debian.org/debian stretch-backports main' | tee /etc/apt/sources.list.d/stretch-backports.list
RUN apt-get update
RUN apt-get install -y build-essential && apt-get install -y zip && apt-get install wget -y
RUN wget https://download.savannah.gnu.org/releases/freetype/freetype-2.12.1.tar.gz && tar xvfz freetype-2.12.1.tar.gz && cd freetype-2.12.1 && ./configure --prefix=/usr/local/freetype/2_12_1 --enable-freetype-config && make && make install
ENV LD_LIBRARY_PATH=/usr/local/freetype/2_12_1/lib
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
