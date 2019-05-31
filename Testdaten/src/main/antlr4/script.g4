grammar script;
 
 @header {
    package thomas.nill.antlr4.script;
}
 
script
    :    sentence ('|' sentence)* ;
 
sentence : constructorCall | OTHERS? normalSentence OTHERS? ;  
 
normalSentence : (gsentence | rsentence | textsentence) listsentence;

constructorCall : TEXT  constructorArgs;

constructorArgs : '[' (TEXT OTHERS?)* ']' ;

listsentence : (sentence)*;

gsentence : '{' sentence '}';

textsentence : TEXT ;

rsentence :  TEXT OTHERS? '(' sentence ')' ;

 
 TEXT : ( '_'|'a'..'z'|'A'..'Z'|'0'..'9'|'.'|'ä'|'ö'|'ü'|'Ä'|'Ö'|'Ü'|'ß')+;
 OTHERS : (' '|','|';'|'!'|'@')+;
 WS  :[\t\n\r]+-> skip;