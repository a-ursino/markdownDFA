import java_cup.runtime.*;

%%


%class lexer
%unicode
%line
%column
%cup

%{
  private Boolean _DEBUG=true;
  StringBuffer bufferStr = new StringBuffer();
 
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
char 			= [^ \\\`\*\_\{\}\[\]\(\)\#\+\-\.\!\"\ \" ]
charM			= {char}+

ipaddresscomp		= [0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]
port			= [1-9][0-9]{0,3}
ipaddress		= ({ipaddresscomp}.){3}{ipaddresscomp}
escape		= %[0-9A-F][0-9A-F]
name              = ([^\n\r%/<>:.\\#\" ]|{escape})+ 
primelevel        = it|com|gov|edu|net|uk|fr|de|ne|jp|ch
domain            = {name}.{name}(.{name})*.{primelevel}
schema            = http|ftp|gopher|https|nntp|file

%xstate STRINGSTATE


%%

{space}				{if(_DEBUG){System.out.printf("SPACE Found [%s]\n",yytext()); }
					return symbol(sym.SPACE);}
{star}				{if(_DEBUG){System.out.printf("STAR Found [%s]\n",yytext());}
					return symbol(sym.STAR);}

{starDouble}		{if(_DEBUG){System.out.printf("STARDOUBLE Found [%s]\n",yytext());
					return symbol(sym.STARDOUBLE);}
/*
{underscore}		{return symbol(sym.UNDERSCORE);} 
{underscoreDouble}	{return symbol(sym.UNDERSCOREDOUBLE);}
{squareBracketO}	{return symbol(sym.SQUAREBRACKETO);}
{squareBracketC}	{return symbol(sym.SQUAREBRACKETC);}
{roundBracketO}		{return symbol(sym.ROUNDBRACKETO);}
{roundBracketC}		{return symbol(sym.ROUNDBRACKETC);}
{doubleQuote}		{return symbol(sym.DOUBLEQUOTE);}
{exclamationMark}	{return symbol(sym.EXCLAMATIONMARK);}
{equal}				{return symbol(sym.EQUAL);}
{dash}				{return symbol(sym.DASH);}
{hash}				{return symbol(sym.HASH);}
{tab}				{return symbol(sym.TAB);}

{string}			{if(_DEBUG){System.out.printf("STRING Found [%s]\n",yytext());}
*/					return symbol(sym.STRING, new String(yytext()));}

{char}				{yybegin(STRINGSTATE);bufferStr.setLength(0);bufferStr.append(yytext());}


<STRINGSTATE>{
{charM}				{if(_DEBUG){System.out.printf("CHARM Found [%s]\n",yytext());}
					bufferStr.append(yytext());}

"\*"    			{if(_DEBUG){System.out.printf("ESCAPE * Found [%s]\n",yytext());}
					bufferStr.append(yytext());}



\* Ê Ê Ê Ê Ê Ê Ê	{if(_DEBUG){System.out.printf("STAR Found [%s]\n",yytext());} 
					yybegin(YYINITIAL); 
 Ê Ê Ê Ê Ê Ê Ê Ê Ê Êreturn symbol(sym.STRING, bufferStr.toString());
 Ê Ê Ê Ê Ê Ê Ê Ê Ê Êreturn symbol(sym.STAR) }

Ê" " Ê Ê Ê Ê Ê Ê    { yybegin(YYINITIAL);
 Ê Ê Ê Ê Ê Ê Ê Ê Ê Êreturn symbol(sym.STRING, bufferStr.toString());
 Ê Ê Ê Ê Ê Ê Ê Ê Ê Êreturn symbol(sym.SPACE)}
}


{schema}"://"({domain}|{ipaddress})(":"{port})?("/"{name})*("/"|("/"{name}"."{name} ("#"{name})?))? 
                         {if(_DEBUG){System.out.printf("URL Found [%s]\n",yytext());}
                         return symbol(sym.URL, new String(yytext()));}

{nl}			{return symbol(sym.NEWLINE);}
//[^"*"]		{System.out.printf("Error [%s]\n",yytext());}
