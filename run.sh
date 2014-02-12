#!/bin/bash

cd $COMPILATROCIOUS_DIR
make
cd grammar_parser
cat ../test/test000.minij | java MiniJava
cat ../test/test001.minij | java MiniJava