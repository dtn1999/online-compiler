#!/bin/sh
ROOTDIR=$PWD

# build container for each language

echo "build cpp container ..."
cd $ROOTDIR/docker/cpp && docker build -t cpp .
echo "building cpp container completed"
echo "build java container ..."

cd $ROOTDIR/docker/java && docker build -t java .
echo "building java container completed"

echo "build python container ..."

cd $ROOTDIR/docker/python && docker build -t python .
echo "building python container completed"

