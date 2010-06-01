#!/bin/bash

if [ "$1" = "run" ]; then
	echo "run only"
	java  Main src.txt
else
	echo "compile and run"
	java  java_cup.Main parser.cup
	jflex scanner.jflex
	javac *.java
	java  Main src.txt
fi
