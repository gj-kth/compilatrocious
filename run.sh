#!/bin/bash

ROOT_DIR=`pwd`
BUILD_DIR="${ROOT_DIR}/build"

cd $BUILD_DIR
#cat $ROOT_DIR/test/compile/test001.minij | java compilatrocious.parser.ParseTree
#cat $ROOT_DIR/test/compile/test002.minij | java compilatrocious.parser.ParseTree
##cat $ROOT_DIR/test/compile/test003.minij | java compilatrocious.parser.ParseTree
#cat $ROOT_DIR/test/compile/test004.minij | java compilatrocious.parser.ParseTree
#cat $ROOT_DIR/test/compile/test005.minij | java compilatrocious.parser.ParseTree
#cat $ROOT_DIR/test/compile/test006.minij | java compilatrocious.parser.ParseTree
java compilatrocious.parser.Test