FROM ubuntu:18.04

RUN apt-get update && \
    apt-get install -y   sudo  \
                         curl  \
                         wget \
                         git-core \
                         gpg \
                         rsync \
                         rlwrap \
                         software-properties-common

# java

# RUN apt-get install -y openjdk-11-jdk 
# JAVA_HOME will be /usr/lib/jvm/java-11-openjdk-amd64/
# ENV JAVA_HOME $(readlink -f /usr/bin/java | sed "s:bin/java::")

RUN mkdir -p /usr/java/default && \
     curl -Ls 'http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.tar.gz' -H 'Cookie: oraclelicense=accept-securebackup-cookie' | \
     tar --strip-components=1 -xz -C /usr/java/default/

#env

ENV HADOOP_VERSION 3.1.2
# ENV JAVA_HOME  /usr/lib/jvm/java-11-openjdk-amd64/
ENV JAVA_HOME  /usr/java/default/
ENV USER=root
ENV HADOOP_HOME=/usr/local/hadoop
ENV PATH $HADOOP_HOME/bin/:$PATH

#hadoop

# RUN wget https://dist.apache.org/repos/dist/release/hadoop/common/hadoop-3.0.3/hadoop-3.0.3.tar.gz.mds
RUN wget http://www-us.apache.org/dist/hadoop/common/hadoop-${HADOOP_VERSION}/hadoop-${HADOOP_VERSION}.tar.gz && \
    tar -xzvf hadoop-${HADOOP_VERSION}.tar.gz && \
    sudo mv hadoop-${HADOOP_VERSION} ${HADOOP_HOME}

COPY .conf/etc.hadoop/* ${HADOOP_HOME}/etc/hadoop/

# RUN useradd -ms /bin/bash hdfs
# RUN useradd -ms /bin/bash yarn


WORKDIR /opt/code/app









