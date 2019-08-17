;PROGRAM SORTUJĄCY TABLICĘ METODĄ INSERTION SORT
ZERO:       DC      INTEGER(0)
CZTERY:     DC      INTEGER(4)
MAXVALUE:   DC      INTEGER(100)
TAB:        DS      25*INTEGER
TAB_SIZE:   DC      INTEGER(25)
SEPARATOR:  DC      CHAR('\n')

MAIN:
            LDA     0, TAB
            LD      1, TAB_SIZE
            LD      2, MAXVALUE
            NEG     2
            LD      3, MAXVALUE
            CALL    INIT_TAB
            CALL    INSERTION_SORT
            LDB     2, SEPARATOR
            CALL    PRINT_TAB
            EXIT

;INICJALIZUJE TABLICĘ WARTOŚCIAMI OD MINVALUE(INCLUSIVE) DO MAXVALUE(EXLUSIVE)
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;RESESTR 1 - TAB_SIZE
;REJESTR 2 - MINVALUE
;REJESTR 3 - MAXVALUE
;OPERUJE NA:
;REJESTRY 1, 2, 4, 5
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
INIT_TAB:
            PUSH    0
            PUSH    1
            LD      4, 3
            SUB     4, 2
L1:         RND     5
            DIV    5, 4
            LD      5, 8
            ADD     5, 2
            ST      5, 0(0)
            LDA     0, 0(4)
            LOOP    1, L1
            POP     1
            POP     0
            RET

;WYŚWIETLA ZAWARTOŚĆ TABLICY
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;RESESTR 1 - TAB_SIZE
;RESESTR 2 - SEPARATOR
;OPERUJE NA:
;REJESTRY 0, 1
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
PRINT_TAB:
            PUSH    0
            PUSH    1
L2:         IOUT    0(0)
            COUT    2
            LDA     0, 0(4)
            LOOP    1, L2
            POP     1
            POP     0
            RET

;SORTUJĘ TABLICĘ - METODĄ INSERTION SORT
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;RESESTR 1 - TAB_SIZE
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6
;WYMAGA DEKLARACJI:
;CZTERY - INTEGER(4)
;ZERO - INTEGER(0)
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
INSERTION_SORT:
            PUSH    0
            PUSH    1
            LD      2, ZERO ;R2: i = 1
            INC     2
L3:         CMP     1, 2
            JLE     L3STOP
            LD      3, 2    ;R3: j = i
            LD      5, CZTERY
            MUL     5, 2
            LD      4, 0
            ADD     4, 5
L4:
            CMP     3, ZERO ;j >? 0
            JLE     L4STOP
            LD      5, 4
            LDA     4, 4(-4)
            LD      6, 4(0)
            CMP     6, 5(0) ;A[j-1] >? A[j]
            JLE     L4STOP
            XCHG    6, 5(0)
            XCHG    6, 4(0)
            DEC     3
            JMP     L4
L4STOP:
            INC     2
            JMP     L3
L3STOP:
            POP     1
            POP     0
            RET