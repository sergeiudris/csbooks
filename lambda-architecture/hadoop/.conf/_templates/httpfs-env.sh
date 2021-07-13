#!/usr/bin/env bash

# Set httpfs specific environment variables here.
#
# hadoop-env.sh is read prior to this file.
#

# HTTPFS config directory
#
# export HTTPFS_CONFIG=${HADOOP_CONF_DIR}

# HTTPFS log directory
#
# export HTTPFS_LOG=${HADOOP_LOG_DIR}

# HTTPFS temporary directory
#
# export HTTPFS_TEMP=${HADOOP_HDFS_HOME}/temp

# The HTTP port used by HTTPFS
#
# export HTTPFS_HTTP_PORT=14000

# The maximum number of HTTP handler threads
#
# export HTTPFS_MAX_THREADS=1000

# The hostname HttpFS server runs on
#
# export HTTPFS_HTTP_HOSTNAME=$(hostname -f)

# The maximum size of HTTP header
#
# export HTTPFS_MAX_HTTP_HEADER_SIZE=65536

# Whether SSL is enabled
#
# export HTTPFS_SSL_ENABLED=false

# The location of the SSL keystore if using SSL
#
# export HTTPFS_SSL_KEYSTORE_FILE=${HOME}/.keystore

# The password of the SSL keystore if using SSL
#
# export HTTPFS_SSL_KEYSTORE_PASS=password