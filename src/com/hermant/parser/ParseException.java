package com.hermant.parser;

class ParseException extends Exception{
    ParseException(String errorMessage, int lineNumber) {
        super(errorMessage + " at line " + lineNumber);
    }
}
