package com.example.contacts_organiser.exceptions;

public class WrongStringFormatException extends Exception{

    public WrongStringFormatException() {
        super("Wrong format. Try again.");
    }
}
