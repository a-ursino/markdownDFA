#!/bin/bash

if [ "$1" = "run" ]; then
	echo "run only"
	java  lexer src.txt
else
	echo "compile and run"
	jflex scanner.jflex
	java  java_cup.Main parser.cup
	java  Main src.txt
fi
