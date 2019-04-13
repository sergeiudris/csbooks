FROM la.hadoop.base




# WORKDIR ${HADOOP_DIR}

COPY ./c ./c

COPY ./etc.hadoop/* ${HADOOP_HOME}/etc/hadoop/
# RUN /usr/local/hadoop/bin/hdfs namenode -format

EXPOSE 9870
EXPOSE 9871
EXPOSE 9820
EXPOSE 35543

