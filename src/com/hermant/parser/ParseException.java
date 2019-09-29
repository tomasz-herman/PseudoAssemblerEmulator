package com.hermant.parser;

public class ParseException extends Exception{
    ParseException(String errorMessage, int lineNumber) {
        super(errorMessage + " at line " + lineNumber);
    }
}
