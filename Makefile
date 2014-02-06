all:
	javacc mini_java.jj
	javac *.java

java:
	javac *.java

javacc:
	javacc mini_java.jj

clean:
	rm *.java
	rm *.class
