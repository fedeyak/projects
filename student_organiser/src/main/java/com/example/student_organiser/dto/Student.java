package com.example.student_organiser.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.stringtemplate.v4.ST;

@Getter
@Setter
@AllArgsConstructor
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "(" + id + ") " + firstName + " " + lastName + ", age " + age + ";";
    }
}
