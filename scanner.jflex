
%%

%unicode
%class lexer
%standalone

nl			= \n|\r|\r\n
tab			= \t
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

ipaddresscomp		= [0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]
port			= [1-9][0-9]{0,3}
ipaddress		= ({ipaddresscomp}.){3}{ipaddresscomp}
escape		= %[0-9A-F][0-9A-F]
name              = ([^\n\r%/<>:.\\#\" ]|{escape})+ 
primelevel        = it|com|gov|edu|net|uk|fr|de|ne|jp|ch
domain            = {name}.{name}(.{name})*.{primelevel}
schema            = http|ftp|gopher|https|nntp|file

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
{tab}			{System.out.printf("TAB Found [%s]\n",yytext());}


{schema}"://"({domain}|{ipaddress})(":"{port})?("/"{name})*("/"|("/"{name}"."{name} ("#"{name})?))? 
                         {System.out.printf("URL found [%s]\n",yytext());}

{nl}			{System.out.printf("New Line\n");}
//[^"*"]		{System.out.printf("Error [%s]\n",yytext());}
