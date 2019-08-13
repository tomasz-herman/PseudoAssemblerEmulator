;BARDZIEJ SKOMPLIKOWANY PRZYKŁAD POKAZUJĄCY REKURENCJĘ W PSEUDOASSEMBLERZE
DWA:         DC  INTEGER(2)
ARG:         DC  INTEGER(32)

MAIN:
        PUSH    ARG             ;fibonacci(32) DAJ FUNKCJI ARGUMENT
        CALL    FIBONACCI_REK   ;WYWOŁAJ FUNKCJĘ
        OUT     1               ;printf(wynik)
        POP     0               ;ZDEJMIJ ZE STOSU ARGUMENT FUNKCJI
        EXIT

;REKURENCYJNIE OBLICZA N-TY WYRAZ CIĄGU FIBONACCIEGO
;ARGUMENTY:
;N - WIERZCHOŁEK STOSU, MAX N = 47
;OPERUJE NA:
;REJESTRY 0, 1
;WYMAGA DEKLARACJI:
;DWA - INTEGER(2)
;ZWRACA:
;N-TY WYRAZ CIĄGU FIBONACCIEGO - REJESTR 1
FIBONACCI_REK:
        LD      0, 11(4)        ;ZAPISZ ARGUMENT FUNKCJI W REJESTRZE 0
        CMP     0, DWA          ;if(n<2)return n;
        JB      STOP_FIBONACCI_REK
        DEC     0               ;n--
        PUSH    0               ;DAJ NASTĘPNEJ REKURENCJI ARGUMENT
        CALL    FIBONACCI_REK   ;fibonacci(n)
        POP     0               ;ZDEJMIJ ZE STOSU ARGUMENT POPRZEDNIEGO WYWOŁANIA
        DEC     0               ;n--
        PUSH    1               ;ZAPAMIĘTAJ WYNIK ZWRÓCONY PRZEZ REKURENCJĘ NA STOSIE
        PUSH    0               ;DAJ NASTĘPNEJ REKURENCJI ARGUMENT
        CALL    FIBONACCI_REK   ;fibonacci(n)
        POP     0               ;ZDEJMIJ ZE STOSU ARGUMENT POPRZEDNIEGO WYWOŁANIA
        POP     0               ;ZDEJMIJ ZE STOSU WYNIK Z POPRZEDNIEJ REKURENCJI
        ADD     1, 0            ;DODAJ DO SIEBIE WYNIKI OBU REKURENCJI
        RET                     ;return fibonacci(n-1) + fibonacci(n-2) ZOSTAW WYNIK W REJESTRZE 1
STOP_FIBONACCI_REK:
        LD      1, 0            ;n<2 -> return n ZAPISZ WYNIK W REJESTRZE 1
        RET
