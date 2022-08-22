grammar compilador;

@header{ 
package compiladores2021;
}

 PA: '(';
 PC: ')'; 
 LA: '{';
 LC: '}';
 CAC:['];
 I_WHILE: 'while';
 I_FOR:'for';
 IF: 'if';
 ELSE: 'else';
 ELSEIF: 'else if'; 
 AND: '&&';
 OR:  '||';
 IGUAL: '='; 
 MENOR: '<';
 MAYOR: '>';
 SUMA: '+';
 RESTA: '-';
 MULT: '*';
 DIVISION: '/'; 
 RETURN: 'return';

//  ENTERO: '-'[0-9]+ | [0-9]+;  5 + (7-2) * 3
 ENTERO:  [0-9]+; 
 PCOMA:';'; 
 TIPO_DATO: 'int' | 'double'| 'char' |'void';
 COMA:',';
 ID: [a-zA-Z_] [a-zA-Z0-9_]*; 



programa: instrucciones  EOF;

instrucciones: instruccion instrucciones
             |
             ;
 
instruccion: declaracion
            |secvar
            |estructuradecontrol                 
            |bloque 
            |llamadaFuncion
            |declaracionFuncion
            |defFuncion
            ;



bloque :  LA instrucciones LC
       ;

estructuradecontrol: i_while
|i_if
|i_for
;    

i_for: I_FOR PA secvar PCOMA ladoA signosdecomparacion ladoB PCOMA secvar PC  instruccion
     ;

i_while : I_WHILE PA ladoA signosdecomparacion ladoB PC instruccion
          ;

declaracion: TIPO_DATO ID  definicion masvar PCOMA 
           ;  

masvar: COMA ID definicion masvar
     |
     ;  
     
definicion: IGUAL ladoB                 
          |
          ;

secvar: ladoA IGUAL ladoB COMA  secvar  //para ahcer int a,b,c; 
     | ladoA IGUAL ladoB PCOMA
     | ladoA IGUAL ladoB                          //Esto es para el for   
     ; 

i_if:  IF PA ladoA signosdecomparacion ladoB PC instruccion  
    |  ELSEIF PA ladoA signosdecomparacion ladoB PC instruccion            
    |  ELSE  instruccion
    ;
 
signosdecomparacion : MENOR
                    | MENOR IGUAL 
                    | MAYOR IGUAL
                    | MAYOR
                    | IGUAL IGUAL
                    | andor
                    ;
ladoA: ENTERO
     | ID
     | oal
     | iCHAR
     ;

ladoB: ENTERO
     | ID
     | oal
     ;

defFuncion: TIPO_DATO ID PA parametro PC   instruccion;

declaracionFuncion: TIPO_DATO ID PA parametro PC PCOMA;

llamadaFuncion: ID PA parametro_Inicializado PC PCOMA;

parametro_Inicializado: ladoA COMA parametro_Inicializado
         | ladoA
         |
         ;

iCHAR: CAC ID CAC;

parametro: TIPO_DATO ID COMA  parametro
         | TIPO_DATO ID parametro
         |
         ;
retorno: RETURN ENTERO
       | RETURN ID //puede ser return oal
       ;

WS : [ \t\n\r] -> skip;

oal: term t ;
term: factores f //Un termino puede ser un factor o un conjunto de suma y restas
    ;
t: SUMA term t 
 | RESTA term t   
 |
 ;

factores: ENTERO
      | '-' ENTERO
      | ID
      | PA oal PC//Al abri parentesis resetea loas prioriudades y vuewlve al comienzo
      ;

f: MULT factores f 
 | DIVISION factores f
 |
 ;

 andor: AND 
 | OR  
 ;