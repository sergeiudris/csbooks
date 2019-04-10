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

# RUN groupadd -f docker && \
#     usermod -aG docker $USER 

RUN curl -sSL https://get.docker.com/ | sh
ADD ./wrapdocker /usr/local/bin/wrapdocker
RUN chmod +x /usr/local/bin/wrapdocker

VOLUME /var/lib/docker

ADD ./daemon.json /etc/docker/daemon.json


# CMD ["wrapdocker"]

EXPOSE 9870
EXPOSE 9871
EXPOSE 9820

