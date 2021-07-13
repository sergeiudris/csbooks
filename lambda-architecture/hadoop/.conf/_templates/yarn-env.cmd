@echo off

@rem User for YARN daemons
if not defined HADOOP_YARN_USER (
  set HADOOP_YARN_USER=%yarn%
)

if not defined YARN_CONF_DIR (
  set YARN_CONF_DIR=%HADOOP_YARN_HOME%\conf
)

if defined YARN_HEAPSIZE (
  @rem echo run with Java heapsize %YARN_HEAPSIZE%
  set JAVA_HEAP_MAX=-Xmx%YARN_HEAPSIZE%m
)

if not defined YARN_LOG_DIR (
  set YARN_LOG_DIR=%HADOOP_YARN_HOME%\logs
)

if not defined YARN_LOGFILE (
  set YARN_LOGFILE=yarn.log
)

@rem default policy file for service-level authorization
if not defined YARN_POLICYFILE (
  set YARN_POLICYFILE=hadoop-policy.xml
)

if not defined YARN_ROOT_LOGGER (
  set YARN_ROOT_LOGGER=%HADOOP_LOGLEVEL%,console
)

set YARN_OPTS=%YARN_OPTS% -Dhadoop.log.dir=%YARN_LOG_DIR%
set YARN_OPTS=%YARN_OPTS% -Dyarn.log.dir=%YARN_LOG_DIR%
set YARN_OPTS=%YARN_OPTS% -Dhadoop.log.file=%YARN_LOGFILE%
set YARN_OPTS=%YARN_OPTS% -Dyarn.log.file=%YARN_LOGFILE%
set YARN_OPTS=%YARN_OPTS% -Dyarn.home.dir=%HADOOP_YARN_HOME%
set YARN_OPTS=%YARN_OPTS% -Dyarn.id.str=%YARN_IDENT_STRING%
set YARN_OPTS=%YARN_OPTS% -Dhadoop.home.dir=%HADOOP_YARN_HOME%
set YARN_OPTS=%YARN_OPTS% -Dhadoop.root.logger=%YARN_ROOT_LOGGER%
set YARN_OPTS=%YARN_OPTS% -Dyarn.root.logger=%YARN_ROOT_LOGGER%
if defined JAVA_LIBRARY_PATH (
  set YARN_OPTS=%YARN_OPTS% -Djava.library.path=%JAVA_LIBRARY_PATH%
)
set YARN_OPTS=%YARN_OPTS% -Dyarn.policy.file=%YARN_POLICYFILE%