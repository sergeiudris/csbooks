FROM ubuntu:18.04


RUN apt-get update && \
    apt-get install -y sudo && \
    apt-get install -y curl && \
    apt-get install -y wget && \
    apt-get install -y git-core && \
    apt-get install -y gpg && \
    apt-get install -y rlwrap && \
    apt-get install -y unzip && \
    apt-get install -y software-properties-common

# RUN add-apt-repository -y ppa:webupd8team/java && \
#     apt-get update && \
#     # sudo apt-get install oracle-java8-installer
#     apt-get install -y oracle-java11-set-default

    # apt install -y openjdk-11-jdk
    # apt install -y oracle-java10-set-default
    # add-apt-repository -y ppa:linuxuprising/java && \
    # apt update && \
    # apt install -y oracle-java10-installer && \
    # apt install -y oracle-java10-set-default
    # apt install default-jre

# RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y  openjdk-8-jdk


RUN curl -O https://download.clojure.org/install/linux-install-1.10.0.411.sh && \
    chmod +x linux-install-1.10.0.411.sh && \
    sudo ./linux-install-1.10.0.411.sh

WORKDIR /opt
ADD c c

# Jess61p4
# RUN curl -O https://manning-content.s3.amazonaws.com/download/e/839f142-236f-4866-8693-abf06add86de/friedman-hill_src_1_jess_se.zip && \
#     unzip friedman-hill_src_1_jess_se.zip && \
#     cp Jess61p4/jess.jar jess.jar
RUN sh c download_jess

RUN mkdir -p /opt/code/app
RUN mkdir -p /root/.m2
WORKDIR /opt/code/app



EXPOSE 35543
EXPOSE 7888
EXPOSE 4000
EXPOSE 8890
EXPOSE 8080
EXPOSE 8081

# CMD ["bash","c", "nrepl_clj"]

