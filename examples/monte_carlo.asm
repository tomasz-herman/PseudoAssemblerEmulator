;PERFORMS MONTE-CARLO SIMULATIONS TO ESTIMATE PI VALUE
RANDOM_MASK:    DC      INTEGER(0xffffff)
RANDOM_DIST:    DC      INTEGER(0x1000000)
FLOAT_ONE:      DC      FLOAT(1)
FLOAT_FOUR:     DC      FLOAT(4)
NEWLINE:        DC      CHAR('\n')
N:              DC      INTEGER(65536)


MAIN:
                LD      3, N
                XOR     1, 1
                XOR     2, 2
LOOP:
                CALL    RANDOM_POINT
                CALL    DIST_SQUARED
                CALL    IS_INSIDE
                ADD     1, 0
                INC     2
                PUSH    1
                FILD    0, 11(0)
                PUSH    2
                FILD    1, 11(0)
                POP     0
                POP     0
                CALL    APPROXIMATE
                FOUT    0
                COUT    NEWLINE
                LOOP    3, LOOP
                EXIT



APPROXIMATE:
                FDIV    0, 1
                FMUL    0, FLOAT_FOUR
                RET


IS_INSIDE:
                XOR     0, 0
                FCMP    0, FLOAT_ONE
                JAE     OUTSIDE
                INC     0
OUTSIDE:
                RET


DIST_SQUARED:
                FMUL    0, 0
                FMUL    1, 1
                FADD    0, 1
                RET


RANDOM_POINT:
                CALL    RANDOM_FLOAT
                FLD     1, 0
                CALL    RANDOM_FLOAT
                RET


RANDOM_FLOAT:
                RND     0
                AND     0, RANDOM_MASK
                PUSH    0
                FPOP    0
                FDIV    0, RANDOM_DIST
                RET