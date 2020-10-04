grammar PseudoAssembler;
@header {
package com.hermant.parser.antlr;
}

prog
    :   line? (EOL line?)* EOF
    ;

line
    :   label* instruction
    |   label* declaration
    |   label+
    ;

label
    :   LABEL
    ;

instruction
    :   no_params_instruction
    |   reg_instruction
    |   mem_instruction
    |   reg_or_mem_instruction
    |   reg_mem_instruction
    |   reg_mem_or_reg_reg_instruction
    ;

no_params_instruction
    :   no_params_op_code
    ;

reg_mem_or_reg_reg_instruction
    :   reg_mem_or_reg_reg_op_code reg ',' mem
    |   reg_mem_or_reg_reg_op_code reg ',' reg
    ;

reg_mem_instruction
    :   reg_mem_op_code reg ',' mem
    ;

reg_or_mem_instruction
    :   reg_or_mem_op_code mem
    |   reg_or_mem_op_code reg
    ;

reg_instruction
    :   reg_op_code reg
    ;

mem_instruction
    :   mem_op_code mem
    ;

declaration
    :   const_declaration
    |   space_declaration
    |   string_declaration
    ;

const_declaration
    :   DC (INT '*')? BYTE_TYPE '(' byte_val ')'
    |   DC (INT '*')? INT_TYPE '(' int_val ')'
    |   DC (INT '*')? FLOAT_TYPE '(' float_val ')'
    |   DC (INT '*')? CHAR_TYPE '(' CHAR ')'
    ;

space_declaration
    :   DS (INT '*')? BYTE_TYPE
    |   DS (INT '*')? INT_TYPE
    |   DS (INT '*')? FLOAT_TYPE
    |   DS (INT '*')? CHAR_TYPE
    ;

string_declaration
    :   DC STRING_TYPE '(' STRING ')'
    ;

byte_val
    :   INT
    |   BIN
    |   HEX
    ;

int_val
    :   SIGNED_INT
    |   INT
    |   BIN
    |   HEX
    ;

float_val
    :   FLOAT
    |   SIGNED_INT
    |   INT
    |   BIN
    |   HEX
    ;

reg
    :   INT
    ;

mem
    :   INT '(' SIGNED_INT ')'
    |   ID
    ;

no_params_op_code
    :   EXIT
    |   RET
    ;

reg_op_code
    :   SIN
    ;

mem_op_code
    :   JUMP
    |   JG
    |   JE
    |   JL
    |   JZ
    ;

reg_mem_or_reg_reg_op_code
    :   ADD
    |   MUL
    |   SUB
    ;

reg_or_mem_op_code
    :   PUSH
    |   OUT
    ;

reg_mem_op_code
    :   LOAD
    |   ST
    ;

//tokens:
DC          :   D C;
DS          :   D S;

EXIT        :   E X I T;
SIN         :   S I N;
RET         :   R E T;

LOAD        :   L D;
ST          :   S T;

JUMP        :   J M P;
JG          :   J G;
JL          :   J L;
JE          :   J E;
JZ          :   J Z;

ADD         :   A D D;
SUB         :   S U B;
MUL         :   M U L;

PUSH        :   P U S H;
OUT         :   O U T;


STRING_TYPE : S T R I N G;
BYTE_TYPE   : B Y T E;
INT_TYPE    : I N T E G E R;
CHAR_TYPE   : C H A R;
FLOAT_TYPE  : F L O A T;

//text:
STRING      :   '"'('\\'.|~'"')*'"';
CHAR        :   '\''('\\'.|~'\'')'\'';

EOL         :   [\r\n]+;
COMMENT     :   ';' ~ [\r\n]* -> skip;
LABEL       :   [a-zA-Z_][a-zA-Z0-9_]*':';
ID          :   [a-zA-Z_][a-zA-Z0-9_]*;
WS          :   [ \t]+ -> skip;

//numeric:
INT         :   [1-9][0-9]*|'0';
SIGNED_INT  :   SIGN? INT;
FLOAT       :   SIGN?( INT ('.'[0-9]*)? | '.'[0-9]+ );

HEX         :   '0x'[0-9a-fA-F]*;

BIN         :   '0b'[01]*;

fragment SIGN:   [+-];

//alphabetic:
fragment A:     [aA];
fragment B:     [bB];
fragment C:     [cC];
fragment D:     [dD];
fragment E:     [eE];
fragment F:     [fF];
fragment G:     [gG];
fragment H:     [hH];
fragment I:     [iI];
fragment J:     [jJ];
fragment K:     [kK];
fragment L:     [lL];
fragment M:     [mM];
fragment N:     [nN];
fragment O:     [oO];
fragment P:     [pP];
fragment Q:     [qQ];
fragment R:     [rR];
fragment S:     [sS];
fragment T:     [tT];
fragment U:     [uU];
fragment V:     [vV];
fragment W:     [wW];
fragment X:     [xX];
fragment Y:     [yY];
fragment Z:     [zZ];