package com.hermant.parser;

public class ParseException extends Exception{
    private int lineNumber;
    private String errorMessage;

    ParseException(String errorMessage, int lineNumber) {
        super(errorMessage + " at line " + lineNumber);
        this.lineNumber = lineNumber;
        this.errorMessage = errorMessage;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
