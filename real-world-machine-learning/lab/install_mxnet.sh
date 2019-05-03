#!/bin/bash

VERSION=1.5.0-SNAPSHOT

git clone --recursive https://github.com/apache/incubator-mxnet.git ~/mxnet
cd ~/mxnet
# git checkout tags/${VERSION} -b my_mxnet
git checkout 372f53100be13b04ca84523dc60deb69ba21e6ac
git submodule update --init --recursive
cd contrib/clojure-package

pwd
ls -a

FILE=project.clj
# ORIGINAL="\"<insert-snapshot-version>\""
# NEW="\"${VERSION}\""

# get the right scala jar version
ORIGINAL=";\[org.apache.mxnet/mxnet-full_2.11-linux-x86_64-cpu \"<insert-snapshot-version>\""
NEW="\[org.apache.mxnet/mxnet-full_2.11-linux-x86_64-cpu \"${VERSION}\""
sed -i "s~${ORIGINAL}~${NEW}~g" $FILE

# remove INTRNAL scala jar dep
ORIGINAL="\[org.apache.mxnet/mxnet-full_2.11 "
NEW=";\[org.apache.mxnet/mxnet-full_2.11 "
sed -i "s~${ORIGINAL}~${NEW}~g" $FILE

# lein test
lein install