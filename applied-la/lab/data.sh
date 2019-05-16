#!/bin/bash/

mkdir -p data

get_mnist(){
  #https://stackoverflow.com/questions/8880603/loop-through-an-array-of-strings-in-bash
  declare -a names=("train-images-idx3-ubyte" "t10k-images-idx3-ubyte" "t10k-labels-idx1-ubyte"  "train-labels-idx1-ubyte")

  BASE_URI=http://yann.lecun.com/exdb/mnist

  echo $BASE_URI

  for name in "${names[@]}"
  do
      echo $name
      curl -o ./data/${name}.gz ${BASE_URI}/${name}.gz 
      gzip -d ./data/${name} 
      # tar -C ./data/${name} -xzvf ./data/${name}.gz
  done
}

get_sdss(){
  URI=https://www.kaggle.com/lucidlenn/sloan-digital-sky-survey/downloads/sloan-digital-sky-survey.zip/2
  # required authentication ((
  
}


  # tar -C ./data/train-images-idx3-ubyte -xzvf ./data/train-images-idx3-ubyte.gz
  # v verbose
  # z - unzip
  # x extract
  # file

"$@"