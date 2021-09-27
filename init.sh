#!/bin/bash
ROOTDIR=$PWD

# colors
BLUE='tput setaf 4'
RED='tput setaf 1'
GREEN='tput setaf 2'
RESET='tput sgr0'

# build container for each language

$BLUE; echo "build cpp container ..."
$RESET;
cd $ROOTDIR/docker/cpp && docker build -t cpp .
$GREEN; echo "building cpp container completed"
$BLUE; echo "build java container ..."
$RESET;
cd $ROOTDIR/docker/java && docker build -t java .
$GREEN; echo "building java container completed"
$RESET;
$BLUE; echo "build python container ..."
$RESET;
cd $ROOTDIR/docker/python && docker build -t python .
$GREEN; echo "building python container completed"
$RESET;
