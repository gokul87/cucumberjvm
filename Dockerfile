FROM ubuntu:18.04
RUN mkdir /usr/local/cucumberjvm
RUN apt-get install maven
RUN mvn --version
COPY . /usr/local/cucumberjvm
WORKDIR /usr/local/cucumberjvm