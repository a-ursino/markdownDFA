#!/bin/bash
echo "Compile and run scanner file."

jflex scanner.jflex
javac lexer.java
java  lexer src.txt