import java_cup.runtime.*;

%%

%public
%class lexer
%implements sym
%unicode
%line
%column
%cup

%{
  StringBuffer string = new StringBuffer();
 
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }
%}




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
equal			= (\=)+
dash			= (\-)+
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

{space}				{return symbol(SPACE);}
{star}				{return symbol(STAR);}
{starDouble}		{return symbol(STARDOUBLE);}
{underscore}		{return symbol(UNDERSCORE);} 
{underscoreDouble}	{return symbol(UNDERSCOREDOUBLE);}
{string}			{return symbol(STRING, new String(yytext()));}
{squareBracketO}	{return symbol(SQUAREBRACKETO);}
{squareBracketC}	{return symbol(SQUAREBRACKETC);}
{roundBracketO}		{return symbol(ROUNDBRACKETO);}
{roundBracketC}		{return symbol(ROUNDBRACKETC);}
{doubleQuote}		{return symbol(DOUBLEQUOTE);}
{exclamationMark}	{return symbol(EXCLAMATIONMARK);}
{equal}				{return symbol(EQUAL);}
{dash}				{return symbol(DASH);}
{hash}				{return symbol(HASH);}
{tab}				{return symbol(TAB);}


{schema}"://"({domain}|{ipaddress})(":"{port})?("/"{name})*("/"|("/"{name}"."{name} ("#"{name})?))? 
                         {return symbol(URL, new String(yytext()));}

{nl}			{System.out.printf("New Line\n");}
//[^"*"]		{System.out.printf("Error [%s]\n",yytext());}
