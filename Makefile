all: parser

parser: javacc
	javac grammar_parser/*.java

javacc: mini_java.jj
	cd grammar_parser; javacc ../mini_java.jj

clean:
	rm -f grammar_parser/*.java
	rm -f grammar_parser/*.class
