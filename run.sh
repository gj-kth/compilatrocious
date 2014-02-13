#!/bin/bash

cd grammar_parser
cat ../test/test000.minij | java MiniJava
cat ../test/test001.minij | java MiniJava
cat ../test/test002.minij | java MiniJava
