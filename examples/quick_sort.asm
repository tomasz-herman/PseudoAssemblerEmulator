;PROGRAM SORTUJĄCY TABLICĘ METODĄ QUICK SORT
MAXVALUE:   DC      INTEGER(100000)
TAB:        DS      16000*INTEGER
TAB_SIZE:   DC      INTEGER(16000)
SEPARATOR:  DC      CHAR('\n')

MAIN:
            LDA     0, TAB
            LD      1, TAB_SIZE
            LD      2, MAXVALUE
            NEG     2
            LD      3, MAXVALUE
            CALL    INIT_TAB
            CALL    QUICK_SORT
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

;SORTUJĘ TABLICĘ - METODĄ QUICK SORT
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;RESESTR 1 - TAB_SIZE
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6, 8
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
QUICK_SORT:
            PUSH    0
            PUSH    1
            ENTER
            XOR     8, 8
            DEC     1
            PUSH    1
            PUSH    8
            INC     8
            INC     8
            ADD     8, 8
            CALL    QUICK_SORT_REK
            LEAVE
            POP     1
            POP     0
            RET

;WYWOŁANIE REKURENCYJNE
;ARGUMENTY:
;STOS(4) - LOW
;STOS(8) - HIGH
;REJESTR 0 - TAB POINTER
;OPERUJE NA:
;REJESTRY 2
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
QUICK_SORT_REK:
            LD      2, 11(4)
            CMP     2, 11(8)
            JGE     RETURN
            CALL    PARTITION
            DEC     1
            PUSH    1
            PUSH    11(8)
            CALL    QUICK_SORT_REK
            POP     2
            POP     2
            INC     2
            PUSH    11(8)
            PUSH    2
            CALL    QUICK_SORT_REK
            POP     2
            POP     2
RETURN:
            RET

;PARTITION WEDŁUG TAB[HIGH]
;ARGUMENTY:
;STOS(4) - LOW
;STOS(8) - HIGH
;REJESTR 0 - TAB POINTER
;OPERUJE NA:
;REJESTRY 2, 3, 4, 5, 6
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - PIVOT INDEX
PARTITION:
            LD      2, 11(12)
            LD      4, 2            ;HIGH
            MUL     2, 8
            ADD     2, 0
            LD      2, 2(0)         ;PIVOT
            LD      1, 11(8)
            LD      3, 1            ;J
            DEC     1               ;I
L3:
            CMP     3, 4
            JGE     L3STOP
            LD      5, 3
            MUL     5, 8
            ADD     5, 0
            LD      5, 5(0)
            CMP     5, 2
            JG      CONTINUE
            INC     1
            LD      6, 1
            MUL     6, 8
            ADD     6, 0
            XCHG    5, 6(0)
            LD      6, 3
            MUL     6, 8
            ADD     6, 0
            ST      5, 6(0)
CONTINUE:
            INC     3
            JMP     L3
L3STOP:
            INC     1
            LD      5, 1
            MUL     5, 8
            ADD     5, 0
            LD      6, 5
            LD      5, 5(0)
            MUL     4, 8
            ADD     4, 0
            LD      3, 4
            LD      3, 3(0)
            ST      3, 6(0)
            ST      5, 4(0)
            RET