grammar compilador;

@header{ 
package compiladores2021;
}

 PA: '(';
 PC: ')';
 LA: '{';
 LC: '}';
 WHILE: 'while';
 AND: '&&';
 OR:  '||';
 IGUAL: '='; 
 MENOR: '<';
 MAYOR: '>';
 SUMA: '+';
 RESTA: '-';
 MULT: '*';
 DIVISION: '/';

//  ENTERO: '-'[0-9]+ | [0-9]+;  5 + (7-2) * 3
 ENTERO:  [0-9]+; 
 PCOMA: ';';
 TIPO_DATO: 'int' | 'double'| 'char';
 COMA: ',';
 ID: [a-zA-Z_] [a-zA-Z0-9_]*;

programa: instrucciones  EOF;  
instrucciones: instruccion instrucciones
             |
             ; 
instruccion: declaracion
| iwhile  
| bloque  
| oal
;

bloque: LA instrucciones LC;
iwhile: WHILE PA ID comparaciones PC bloque;
declaracion: TIPO_DATO secvar PCOMA {System.out.println("Encontrado");} 
           ;  
secvar: ID igualdad COMA  secvar  //para ahcer int a,b,c;
      | ID igualdad    //para ahcer int a,b,c;
      | ID COMA secvar 
      | ID 
      ; 

igualdad:IGUAL ID
     | IGUAL ENTERO
     ; 
oal: term t 
    |comparaciones
    |andor  
    ;

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

 comparaciones: MAYOR oal comparaciones
 | MENOR oal comparaciones
 | IGUAL IGUAL oal comparaciones
 | MAYOR IGUAL oal comparaciones
 | MENOR IGUAL oal comparaciones
 | IGUAL IGUAL oal comparaciones
 |
 ;
 
 andor: AND oal andor
 | OR oal andor
 |
 ;