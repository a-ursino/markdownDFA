import java_cup.runtime.*;

%%

%class lexer
%unicode
%line
%column
%cup

%{
	private Boolean _DEBUG=true;
	private Symbol symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn+1);
	}

	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn+1, value);
	}
%}


nl					= [\n|\r|\r\n]
tab					= [\t\f]  
space				= " "
newstring                       = ([\n|\r|\r\n]{3,100})([a-zA-Z0-9.,;:]+)
string				= [a-zA-Z0-9.,;:]+
newstar                       = ([\n|\r|\r\n]{3,100})(\*)
star				= \*
newstarDouble                   = ([\n|\r|\r\n]{3,100})(\*\*)                                
starDouble			= \*\*
newunderscore                   = ([\n|\r|\r\n]{3,100})(\_)
underscore			= \_
newunderscoreDouble             = ([\n|\r|\r\n]{3,100})(__)
underscoreDouble	        = __
squareBracketO		= \[
squareBracketC		= \]
roundBracketO		= \(
roundBracketC		= \)
equal				= (\=)+
hash				= #
rowSpaces			= [" "]{1,3} 
startOrdList		= [" "]{1,3}[0-9]+.[" "]*
startUnList			= [" "]{1,3}(\*|\+|\-)((" ")+| \t)
startPre			= [" "]{4}
id					= [A-Za-z_][A-Za-z0-9_]*
IntegerNumber                           = 0 | [1-9][0-9]*
charValue                               = [a-zA-Z]
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? 
FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+




%xstate ESCAPESTATE CODESTATE CHARVALUE


%%
                                             
^{startPre}			{if(_DEBUG){System.out.printf("Start pre code Found [%s]\n",yytext());}
						return symbol(sym.STARTPRE);} 
                                                
^{startUnList}		{if(_DEBUG){System.out.printf("Start unordered List Found [%s]\n",yytext());}
						return symbol(sym.STARTUNORDLIST);}

^{startOrdList}		{if(_DEBUG){System.out.printf("startOrdList Found [%s]\n",yytext());}
						return symbol(sym.STARTORDLIST);}
                                                
^{rowSpaces}		{if(_DEBUG){System.out.printf("ROWSPACE Found [%s]\n",yytext());}
						return symbol(sym.ROWSPACE);}
                                                
  
{space}				{if(_DEBUG){System.out.printf("SPACE Found [%s]\n",yytext()); }
						return symbol(sym.SPACE);}
                                                
{nl}				{ }


{newstar}                        {if(_DEBUG){System.out.printf("NEWSTAR Found [%s]\n","*");}
						return symbol(sym.NEWSTAR);}       

{star}				{if(_DEBUG){System.out.printf("STAR Found [%s]\n",yytext());}
						return symbol(sym.STAR);}

{newstarDouble}		{if(_DEBUG){System.out.printf("NEWSTARDOUBLE Found [%s]\n","**");}
						return symbol(sym.NEWSTARDOUBLE);}

                                                                                                
{starDouble}		{if(_DEBUG){System.out.printf("STARDOUBLE Found [%s]\n",yytext());}
						return symbol(sym.STARDOUBLE);}

{newunderscore}		{if(_DEBUG){System.out.printf("NEWUNDERSCORE Found [%s]\n","_"); }
						return symbol(sym.NEWUNDERSCORE);}

{underscore}		{if(_DEBUG){System.out.printf("UNDERSCORE Found [%s]\n",yytext()); }
                                                return symbol(sym.UNDERSCORE);}

{newunderscoreDouble}	{if(_DEBUG){System.out.printf("NEWUNDERSCOREDOUBLE Found [%s]\n","__");}
						return symbol(sym.NEWUNDERSCOREDOUBLE);}

                                                
{underscoreDouble}	{if(_DEBUG){System.out.printf("UNDERSCOREDOUBLE Found [%s]\n",yytext());}
						return symbol(sym.UNDERSCOREDOUBLE);}

                                                
{newstring}                               {String p= new String(yytext());
                                          int i= p.lastIndexOf('\n');
                                          System.out.printf("NEWSTRING Found [%s]\n",new String(p.substring(i+1)));
                                                  return symbol(sym.NEWSTRING, new String(p.substring(i+1))); }
                                
{string}			{if(_DEBUG){System.out.printf("STRING Found [%s]\n",yytext());}
						return symbol(sym.STRING, new String(yytext()));}
                                                
{squareBracketO}	{if(_DEBUG){System.out.printf("SQUAREBRACKETO Found [%s]\n",yytext());}
						return symbol(sym.SQUAREBRACKETO);}
                                                
{squareBracketC}	{if(_DEBUG){System.out.printf("SQUAREBRACKETC Found [%s]\n",yytext());}
						return symbol(sym.SQUAREBRACKETC);}
                                                
{roundBracketO}		{if(_DEBUG){System.out.printf("ROUNDBRACKETO Found [%s]\n",yytext());}
						return symbol(sym.ROUNDBRACKETO);}
                                                
{roundBracketC}		{if(_DEBUG){System.out.printf("ROUNDBRACKETC Found [%s]\n",yytext());}
						return symbol(sym.ROUNDBRACKETC);}
                                                
                                          
{equal}				{if(_DEBUG){System.out.printf("EQUAL Found [%s]\n",yytext());}
						return symbol(sym.EQUAL);}


^{hash}				{ System.out.printf("HASH Found on beginning of a row [%s]\n",yytext());
					  return symbol(sym.HASH_BEG);
					}
{hash}				{if(_DEBUG){System.out.printf("HASH Found [%s]\n",yytext());}
						return symbol(sym.HASH);}



//ESCAPE STATE                                               

\\					{ yybegin(ESCAPESTATE);
						System.out.printf("Start ESCAPESTATE \n"); }
<ESCAPESTATE> {

"*"      			{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('*'));
					}
"#"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('#'));
					}
"!"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('!'));
					}
"+"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('+'));
					}
"-"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('-'));
					}
"_"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('_'));
					}
"_"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('#'));
					}
"("					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('('));
					}
")"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character(')'));
					}
"["					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('['));
					}
"]"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character(']'));
					}
"{"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('{'));
					}
"}"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('}'));
					}
"."					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('.'));
					}
"`"					{ System.out.printf("Literal Char Found [%s]\n",yytext());
					  yybegin(YYINITIAL);
					  return symbol(sym.LITCHAR,new Character('`'));
					}

}



//CHAR_VALUE STATE

 <CHARVALUE> {
  
{charValue}                   {if(_DEBUG){System.out.printf("_CHARVALUE Found [%s]\n",yytext());}
					return symbol(sym.CHAR_VALUE, new String(yytext())); }
                                              
    "'"                         { System.out.printf("END CODESTAMP [%s]\n",yytext());
					  yybegin(CODESTATE); }
              }

            
                                        
           
//CODESTATE STATE

"<?"				{ yybegin(CODESTATE);
					System.out.printf("Start CODESTATE \n"); } 
<CODESTATE> {

  
/* keywords */
"boolean"			{ if(_DEBUG){System.out.printf("_BOOLEAN Found [%s]\n",yytext());}
						return symbol(sym.BOOLEAN); }
"char"				{ if(_DEBUG){System.out.printf("_CHAR Found [%s]\n",yytext());}
						return symbol(sym.CHAR); }
"else"				{ if(_DEBUG){System.out.printf("_ELSE Found [%s]\n",yytext());}
						return symbol(sym.ELSE); }
"float"				{ if(_DEBUG){System.out.printf("_FLOAT Found [%s]\n",yytext());}
						return symbol(sym.FLOAT); }
"int"				{ if(_DEBUG){System.out.printf("_INT Found [%s]\n",yytext());}
						return symbol(sym.INT); }
"if"				{ if(_DEBUG){System.out.printf("_IF Found [%s]\n",yytext());}
						return symbol(sym.IF); }
"while"				{ if(_DEBUG){System.out.printf("_WHILE Found [%s]\n",yytext());}
						return symbol(sym.WHILE); }
";"					{ if(_DEBUG){System.out.printf("_SEMICOLON Found [%s]\n",yytext());}
						return symbol(sym.SEMICOLON); }
","					{ if(_DEBUG){System.out.printf("_COMMA Found [%s]\n",yytext());}
						return symbol(sym.COMMA); }
"("					{ if(_DEBUG){System.out.printf("_ROUNDBRACKETO Found [%s]\n",yytext());}
						return symbol(sym.ROUNDBRACKETO); }
")"					{ if(_DEBUG){System.out.printf("_ROUNDBRACKETC Found [%s]\n",yytext());}
						return symbol(sym.ROUNDBRACKETC); }
"{"					{ if(_DEBUG){System.out.printf("_LEFTBRACE Found [%s]\n",yytext());}
						return symbol(sym.LEFTBRACE); }
"}"					{ if(_DEBUG){System.out.printf("_RIGHTBRACE Found [%s]\n",yytext());}
						return symbol(sym.RIGHTBRACE); }
"["					{ if(_DEBUG){System.out.printf("_SQUAREBRACKETO Found [%s]\n",yytext());}
						return symbol(sym.SQUAREBRACKETO); }
"]"					{ if(_DEBUG){System.out.printf("_SQUAREBRACKETC Found [%s]\n",yytext());}
						return symbol(sym.SQUAREBRACKETC); }


/*operators*/
"="				{ if(_DEBUG){System.out.printf("EQUAL Found [%s]\n",yytext());}
					return symbol(sym.EQUAL); }
">"				{ if(_DEBUG){System.out.printf("GT Found [%s]\n",yytext());}
					return symbol(sym.GT); }
"<"				{ if(_DEBUG){System.out.printf("LT Found [%s]\n",yytext());}
					return symbol(sym.LT); }
"!"				{ if(_DEBUG){System.out.printf("NOT Found [%s]\n",yytext());}
					return symbol(sym.NOT); }

"=="			{ if(_DEBUG){System.out.printf("EQUAL_EQUAL Found [%s]\n",yytext());}
					return symbol(sym.EQ_EQ); }
"<="			{ if(_DEBUG){System.out.printf("LTEQ Found [%s]\n",yytext());}
					return symbol(sym.LTEQ); }
"=>"			{ if(_DEBUG){System.out.printf("GTEQ Found [%s]\n",yytext());}
					return symbol(sym.GTEQ); }
"!="			{ if(_DEBUG){System.out.printf("NOT_EQ Found [%s]\n",yytext());}
					return symbol(sym.NOT_EQ); }
"&&"			{ if(_DEBUG){System.out.printf("AND_AND Found [%s]\n",yytext());}
					return symbol(sym.AND_AND); }
"||"			{ if(_DEBUG){System.out.printf("OR_OR Found [%s]\n",yytext());}
					return symbol(sym.OR_OR); }
"-"				{ if(_DEBUG){System.out.printf("MINUS Found [%s]\n",yytext());}
					return symbol(sym.MINUS); }
"+"				{ if(_DEBUG){System.out.printf("PLUS Found [%s]\n",yytext());}
					return symbol(sym.PLUS,new Character('+')); }
"/"				{ if(_DEBUG){System.out.printf("DIV Found [%s]\n",yytext());}
					return symbol(sym.DIV); }

"*"				{ if(_DEBUG){System.out.printf("MULTIPLY Found [%s]\n",yytext());}
					return symbol(sym.MULTIPLY); }

"\""                             { if(_DEBUG){System.out.printf("MARKS Found [%s]\n",yytext());}
                                       return symbol(sym.MARKS); }

                                        
/**NUOVI**/

//per entra nello state CHAR_VALUE
"'"                                                { yybegin(CHARVALUE);
					System.out.printf("Start _CHARVALUE \n"); }
                                                                            
                                        
{IntegerNumber}                         {if(_DEBUG){System.out.printf("Integer Value Found [%s]\n",yytext());}
					return symbol(sym.VALUE, new Integer(yytext())); }
                                        
{FloatLiteral}                          {if(_DEBUG){System.out.printf("Float Value Found [%s]\n",yytext());}
					return symbol(sym.FLOAT_VALUE, new Float(yytext())); }


"TRUE"|"true"|"FALSE"|"false"           {if(_DEBUG){System.out.printf("Boolean Value Found [%s]\n",yytext());}
					return symbol(sym.BOOL_VALUE, new Boolean(yytext())); }                                      
                                      
                                      
//Tipo dato
"String"			{if(_DEBUG){System.out.printf("_STRING Found [%s]\n",yytext());}
						return symbol(sym.STRING_TYPE);}

"@function"                      {if(_DEBUG){System.out.printf("_FUNCTION Found [%s]\n",yytext());}
						return symbol(sym.FUNCTION);}                          
                                                
                                              
                                                
{id}			{ if(_DEBUG){System.out.printf("ID_NAME Found [%s]\n",yytext());}
							return symbol(sym.ID_NAME,yytext()); }
                                                        
"?>"      			{ System.out.printf("END CODESTATE [%s]\n",yytext());
					yybegin(YYINITIAL);}
					
{nl}|{tab}|" "			{ /*ignore */}		
.					{ if(_DEBUG){System.out.printf("ERRORTEXT [%s]\n",yytext());}
						return symbol(sym.SEMICOLON); }
}

<<EOF>>				{System.out.printf("EOF FOUND\n",yytext());
					return symbol(sym.EOF); 
					}