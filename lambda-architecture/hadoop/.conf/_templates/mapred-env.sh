##
## THIS FILE ACTS AS AN OVERRIDE FOR hadoop-env.sh FOR ALL
## WORK DONE BY THE mapred AND RELATED COMMANDS.
##
## Precedence rules:
##
## mapred-env.sh > hadoop-env.sh > hard-coded defaults
##
## MAPRED_xyz > HADOOP_xyz > hard-coded defaults
##

###
# Job History Server specific parameters
###

# Specify the max heapsize for the JobHistoryServer.  If no units are
# given, it will be assumed to be in MB.
# This value will be overridden by an Xmx setting specified in HADOOP_OPTS,
# and/or MAPRED_HISTORYSERVER_OPTS.
# Default is the same as HADOOP_HEAPSIZE_MAX.
#export HADOOP_JOB_HISTORYSERVER_HEAPSIZE=

# Specify the JVM options to be used when starting the HistoryServer.
# These options will be appended to the options specified as HADOOP_OPTS
# and therefore may override any similar flags set in HADOOP_OPTS
#export MAPRED_HISTORYSERVER_OPTS=

# Specify the log4j settings for the JobHistoryServer
# Java property: hadoop.root.logger
#export HADOOP_JHS_LOGGER=INFO,RFA
