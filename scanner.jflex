
%%

%unicode
%class lexer
%standalone

nl			= \n|\r|\r\n
space			= " "
string			= [a-zA-Z0-9.,;:]+
star			= \*
starDouble		= \*\* 
underscore		= _ 
underscoreDouble	= __
squareBracketO		= \[
squareBracketC		= \]
roundBracketO		= \(
roundBracketC		= \)
doubleQuote		= \"
exclamationMark		= \!
equal			= "="|["="]+
dash			= -|[-]+
hash			= #


%%

{space}			{System.out.printf("SPACE Found [%s]\n",yytext());}
{star}			{System.out.printf("STAR  Found [%s]\n",yytext());}
{starDouble}		{System.out.printf("STARDOUBLE Found [%s]\n",yytext());}
{underscore}		{System.out.printf("UNDERSCORE Found [%s]\n",yytext());} 
{underscoreDouble}	{System.out.printf("UNDERSCOREDOUBLE Found [%s]\n",yytext());}
{string}		{System.out.printf("STRING Found [%s]\n",yytext());}
{squareBracketO}	{System.out.printf("SQUAREBRACKETO Found [%s]\n",yytext());}
{squareBracketC}	{System.out.printf("SQUAREBRACKETC Found [%s]\n",yytext());}
{roundBracketO}		{System.out.printf("ROUNDBRACKETO Found [%s]\n",yytext());}
{roundBracketC}		{System.out.printf("ROUNDBRACKETC Found [%s]\n",yytext());}
{doubleQuote}		{System.out.printf("DOUBLEQUOTE Found [%s]\n",yytext());}
{exclamationMark}	{System.out.printf("EXCLAMATIONMARK Found [%s]\n",yytext());}
{equal}			{System.out.printf("EQUAL Found [%s]\n",yytext());}
{dash}			{System.out.printf("DASH Found [%s]\n",yytext());}
{hash}			{System.out.printf("HASH Found [%s]\n",yytext());}

{nl}			{System.out.printf("New Line\n");}
//[^"*"]		{System.out.printf("Error [%s]\n",yytext());}
