FROM ubuntu:18.04

RUN apt-get update && \
    apt-get install -y sudo && \
    apt-get install -y curl && \
    apt-get install -y wget && \
    apt-get install -y git-core && \
    apt-get install -y gpg && \
    apt-get install -y rlwrap && \
    apt-get install -y software-properties-common


# RUN apt-get install -y openjdk-8-jdk

# java
RUN mkdir -p /usr/java/default && \
     curl -Ls 'http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz' -H 'Cookie: oraclelicense=accept-securebackup-cookie' | \
     tar --strip-components=1 -xz -C /usr/java/default/

# RUN wget https://dist.apache.org/repos/dist/release/hadoop/common/hadoop-3.0.3/hadoop-3.0.3.tar.gz.mds
RUN wget http://www-us.apache.org/dist/hadoop/common/hadoop-3.0.3/hadoop-3.0.3.tar.gz && \
    tar -xzvf hadoop-3.0.3.tar.gz && \
    sudo mv hadoop-3.0.3 /usr/local/hadoop


# ENV JAVA_HOME $(readlink -f /usr/bin/java | sed "s:bin/java::")
ENV JAVA_HOME  /usr/java/default





