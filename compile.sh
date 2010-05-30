#!/bin/bash

if [ "$1" = "run" ]; then
	echo "run only"
	java  lexer src.txt
else
	echo "compile and run"
	jflex scanner.jflex
	javac lexer.java
	java  lexer src.txt

fi
