#!/bin/bash

# test the hadoop cluster by running wordcount

# create input files 
DIR=input
mkdir $DIR
echo "Hello Docker" >$DIR/file2.txt
echo "Hello Hadoop" >$DIR/file1.txt

# create input directory on HDFS
hadoop fs -mkdir -p input

# put input files to HDFS
hdfs dfs -put ./$DIR/* input

# run wordcount 
hadoop jar $HADOOP_HOME/share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.7.1-sources.jar org.apache.hadoop.examples.WordCount input output

# print the input files
echo -e "\ninput file1.txt:"
hdfs dfs -cat $DIR/file1.txt

echo -e "\ninput file2.txt:"
hdfs dfs -cat $DIR/file2.txt

# print the output of wordcount
echo -e "\nwordcount output:"
hdfs dfs -cat output/part-r-00000

