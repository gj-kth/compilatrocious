#!/bin/bash

ROOT_DIR=`pwd`
BUILD_DIR="${ROOT_DIR}/build"

cd $BUILD_DIR
cat $ROOT_DIR/test/test001.minij | java compilatrocious.parser.MiniJava
cat $ROOT_DIR/test/test002.minij | java compilatrocious.parser.MiniJava
cat $ROOT_DIR/test/test003.minij | java compilatrocious.parser.MiniJava
cat $ROOT_DIR/test/test004.minij | java compilatrocious.parser.MiniJava
cat $ROOT_DIR/test/test005.minij | java compilatrocious.parser.MiniJava
cat $ROOT_DIR/test/test006.minij | java compilatrocious.parser.MiniJava
