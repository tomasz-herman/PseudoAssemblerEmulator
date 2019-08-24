;PROGRAM SORTUJĄCY TABLICĘ METODĄ INSERTION SORT
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
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
INSERTION_SORT:
            PUSH    0
            PUSH    1
            DEC     1
            XOR     5, 5
L3:
            LDA     2, 0(0)
            LDA     0, 0(4)
            LD      4, 0(0)
            LD      3, 11(0)
            SUB     3, 1
L4:
            CMP     3, 5
            JL      BREAK
            CMP     4, 2(0)
            JGE     BREAK
            LD      6, 2(0)
            ST      6, 2(4)
            LDA     2, 2(-4)
            LOOP    3, L4
BREAK:
            ST      4, 2(4)
            LOOP    1, L3
STOP:
            POP     1
            POP     0
            RET