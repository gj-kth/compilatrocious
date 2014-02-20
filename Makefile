.PHONY: all parser javacc clean
all: parser

parser: javacc
	javac src/grammar_parser/*.java

javacc: src/mini_java.jj
	cd src/grammar_parser; ../../lib/javacc-5.0/bin/javacc ../mini_java.jj

clean:
	rm -f src/grammar_parser/*.java
	rm -f src/grammar_parser/*.class
