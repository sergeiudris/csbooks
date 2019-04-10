FROM la.hadoop.base


RUN  apt-get update -qq && apt-get install -qqy \
        apt-transport-https \
        ca-certificates \
        curl \
        software-properties-common \
        lxc \
        iptables

# RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - && \
#     add-apt-repository \
#    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
#    $(lsb_release -cs) \
#    stable edge" && \
#    apt-get update && \
#    apt-get install -y docker-ce

# RUN curl -L "https://github.com/docker/compose/releases/download/1.23.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \
#     chmod +x /usr/local/bin/docker-compose




RUN curl -sSL https://get.docker.com/ | sh
ADD ./wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

VOLUME /var/lib/docker

ADD ./daemon.json /etc/docker/daemon.json




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


RUN groupadd -f docker && \
    usermod -aG docker $USER && \
    groupadd -f hadoop && \
    usermod -aG hadoop $USER 

# CMD ["wrapdocker"]

EXPOSE 9870
EXPOSE 9871
EXPOSE 9820
EXPOSE 35543
EXPOSE 7888

