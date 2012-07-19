
@echo off 
IF [%1]==[] GOTO ERROR

IF [%2]==[] GOTO DEFAULTFILE

GOTO SPECIFIEDFILE

:DEFAULTFILE
IF /I %1 EQU run (
	echo "Eseguire (senza ricompilare) usando come file di input src.txt"
	java Main src.txt
	GOTO END 
) 

IF /I %1 EQU compile (
	echo "Ricompilare l'intero progetto "
	java java_cup.Main parser.cup
	javac lexer.java
	javac sym.java
	javac parser.java
	javac  Main.java
	java Main %2
	GOTO END 
)
IF /I %1 EQU draw (
	echo "Eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file di input src.txt""
	java java_cup.MainDrawTree parser.cup
	javac parser.java
	java Main src.txt
	GOTO END 
)
echo "Le azioni possibili sono:"
echo "run"
echo "compile"
echo "draw"

GOTO END 


:SPECIFIEDFILE
IF /I %1 EQU run (
	IF EXIST %2 (
		echo "Eseguire (senza ricompilare) usando come file d'ingresso il file specificato"
		java Main %2
		GOTO END 
	) ELSE (
		GOTO FILENOTEXIST
	)
) 

IF /I %1 EQU draw (
	IF EXIST %2 (
		echo "Eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file d'ingresso il file specificato"
		java java_cup.MainDrawTree parser.cup
		javac parser.java
		java Main %2
		GOTO END 
	) ELSE (
		GOTO FILENOTEXIST
	)
)

IF /I %1 EQU compile (
	IF EXIST %2 (
		echo "Ricompilare ed eseguire usando come file d'ingresso il file specificato"
		java java_cup.Main parser.cup
		javac lexer.java
		javac sym.java
		javac parser.java
		javac  Main.java
		java Main %2
		GOTO END 
	) ELSE (
		GOTO FILENOTEXIST
	)
)

echo "Le azioni possibili sono:"
echo "run"
echo "compile"
echo "draw"or
GOTO END 

:DRAW
echo "compile and run"
        java java_cup.MainDrawTree parser.cup
        javac lexer.java
		javac sym.java
        javac parser.java
        javac  Main.java 
        java Main src.txt
        
GOTO END 
:RUN 

echo "compile and run"
        java java_cup.Main parser.cup
        javac lexer.java
		javac sym.java
        javac parser.java
        javac  Main.java 
        java Main src.txt
        
GOTO END


:ERROR
   echo "Uso:"
   echo "1) Per eseguire (senza ricompilare) usando come file di input src.txt"
   echo "compile.bat run"
   echo "2) Per eseguire (senza ricompilare) usando come file d'ingresso il file specificato"
   echo "compile.bat run FileInput"
   echo "---------------------------------"
   echo "3) Per ricompilare l'intero progetto "
   echo "compile.bat compile"
   echo "4) Per ricompilare ed eseguire usando come file d'ingresso il file specificato"
   echo "compile.bat compile FileInput"
   echo "---------------------------------"
   echo "5) Per eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file di input src.txt"
   echo "compile.bat draw "
   echo "6) Per eseguire (senza ricompilare) in modalita' visualizzazione albero usando come file d'ingresso il file specificato"
   echo "compile.bat draw FileInput"
GOTO END


:FILENOTEXIST
echo "il file specificato non esiste"
GOTO END

:END


