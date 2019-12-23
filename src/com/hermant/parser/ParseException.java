package com.hermant.parser;

public class ParseException extends Exception{
    private int lineNumber;

    ParseException(String errorMessage, int lineNumber) {
        super(errorMessage + " at line " + lineNumber);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
