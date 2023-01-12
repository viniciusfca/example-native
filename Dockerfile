FROM buildpack-deps:22.04
ENV LD_LIBRARY_PATH=/usr/lib/x86_64-linux-gnu
RUN mkdir /app/
ADD . /app
WORKDIR /app
COPY example-native /app
CMD ["./example-native"]
