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
ENV PATH $JAVA_HOME/bin/:$PATH

#hadoop

# RUN wget https://dist.apache.org/repos/dist/release/hadoop/common/hadoop-3.0.3/hadoop-3.0.3.tar.gz.mds
RUN wget http://www-us.apache.org/dist/hadoop/common/hadoop-${HADOOP_VERSION}/hadoop-${HADOOP_VERSION}.tar.gz && \
    tar -xzvf hadoop-${HADOOP_VERSION}.tar.gz && \
    sudo mv hadoop-${HADOOP_VERSION} ${HADOOP_HOME}

COPY .conf/etc.hadoop/* ${HADOOP_HOME}/etc/hadoop/

# RUN useradd -ms /bin/bash hdfs
# RUN useradd -ms /bin/bash yarn


# RUN apt-get update && \
#     apt-get install -y openjdk-11-jdk

RUN curl -O https://download.clojure.org/install/linux-install-1.10.0.442.sh && \
    chmod +x linux-install-1.10.0.442.sh && \
    sudo ./linux-install-1.10.0.442.sh

WORKDIR /tmp

ENV LEIN_VERSION=2.8.3
ENV LEIN_INSTALL=/usr/local/bin/

# Download the whole repo as an archive
RUN mkdir -p $LEIN_INSTALL \
  && wget -q https://raw.githubusercontent.com/technomancy/leiningen/$LEIN_VERSION/bin/lein-pkg \
  # && echo "Comparing lein-pkg checksum ..." \
  # && echo "019faa5f91a463bf9742c3634ee32fb3db8c47f0 *lein-pkg" | sha1sum -c - \
  && mv lein-pkg $LEIN_INSTALL/lein \
  && chmod 0755 $LEIN_INSTALL/lein \
  && wget -q https://github.com/technomancy/leiningen/releases/download/$LEIN_VERSION/leiningen-$LEIN_VERSION-standalone.zip \
  && wget -q https://github.com/technomancy/leiningen/releases/download/$LEIN_VERSION/leiningen-$LEIN_VERSION-standalone.zip.asc \
  && rm leiningen-$LEIN_VERSION-standalone.zip.asc \
  && mkdir -p /usr/share/java \
  && mv leiningen-$LEIN_VERSION-standalone.zip /usr/share/java/leiningen-$LEIN_VERSION-standalone.jar


WORKDIR /opt/code/app









