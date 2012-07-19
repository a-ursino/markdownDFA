#!/bin/bash

function DisplayMenu {
   echo ""
   echo ""
   echo "Uso:"
   echo "1) Per eseguire (senza ricompilare) usando come file di input src.txt"
   echo "./compile.sh run"
   echo "2) Per eseguire (senza ricompilare) usando come file d'ingresso il file specificato"
   echo "./compile.sh run FileInput"
   echo "---------------------------------"
   echo "3) Per ricompilare l'intero progetto "
   echo "./compile.sh compile"
   echo "4) Per ricompilare ed eseguire usando come file d'ingresso il file specificato"
   echo "./compile.sh compile FileInput"
   echo "---------------------------------"
   echo "5) Per eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file di input src.txt"
   echo "./compile.sh draw "
   echo "6) Per eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file d'ingresso il file specificato"
   echo "./compile.sh draw FileInput"
   echo "7) Per eliminare tutti i file temporanei"
   echo "./compile.sh clean"
}

if [ "$1" = "" -a "$2" = "" ]; then
	echo ""
	echo "Specificare almeno un parametro"
	DisplayMenu
	exit
fi

if [ "$1" != "" -a "$2" = "" ]; then
	if [ "$1" = "run" ]; then
		echo ""
		echo "Eseguire (senza ricompilare) usando come file di input src.txt"
		java  Main src.txt
		exit
	fi
	if [ "$1" = "compile" ]; then
		echo ""
		echo "Ricompilare l'intero progetto "
		javac ./st/*.java
		java  java_cup.Main parser.cup
		javac sym.java
		jflex scanner.jflex
		javac *.java
		exit
	fi
	if [ "$1" = "draw" ]; then
		echo ""
		echo "eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file di input src.txt"
		java java_cup.MainDrawTree parser.cup
		javac parser.java	
		java  Main src.txt
		exit
	fi
	if [ "$1" = "clean" ]; then
		echo ""
		echo "Eliminare tutti i file temporanei"
		rm -v *.class
		rm -v ./st/*.class
		rm -v lexer.java
		rm -v lexer.java~
		rm -v parser.java
		rm -v sym.java
		exit
	fi
	echo ""
	echo "Il parametro $1 non rientra tra le opzioni disponibili"
	DisplayMenu
	exit;
fi

if [ "$1" != " " -a "$2" != " " ]; then
	if [ "$1" = "run" ]; then
		if [ -f $2 ]; then 
			echo ""
			echo "Eseguire (senza ricompilare) usando come file d'ingresso il file specificato"
			java  Main $2
			exit
		else 
			echo ""
			echo "Il file $2 non esiste o non e' un file"
			exit
		fi
	fi
	if [ "$1" = "draw" ]; then
		if [ -f $2 ]; then 
			echo ""
			echo "Eseguire (senza ricompilare) in modalità visualizzazione albero usando come file d'ingresso il file specificato"
			java java_cup.MainDrawTree parser.cup
			java  Main $2
			exit
		else 
			echo ""
			echo "Il file $2 non esiste o non e' un file"
			exit
		fi
	fi
	if [ "$1" = "compile" ]; then
		if [ -f $2 ]; then 
			echo ""
			echo "Ricompilare ed eseguire usando come file d'ingresso il file specificato"
			javac ./st/*.java
			java  java_cup.Main parser.cup
			javac sym.java
			jflex scanner.jflex
			javac *.java
			java  Main $2
			exit
		else 
			echo ""
			echo "Il file $2 non esiste o non e' un file"
			exit
		fi
	fi

	DisplayMenu
	exit
	
fi


