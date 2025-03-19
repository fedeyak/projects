package com.example.student_organiser.shell_manager;

import com.example.student_organiser.dto.Student;
import com.example.student_organiser.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class StudentShellManager {

    @Autowired
    StudentService studentService;

    @ShellMethod(value = "Add a new student", key = "add")
    public void addStudent(@ShellOption(value = "--fn") String firstName,
                           @ShellOption(value = "--ln") String lastName,
                           @ShellOption(value = "--age") int age) {
        studentService.addStudent(firstName, lastName, age);
    }


    @ShellMethod(value = "removing student by id", key = "rem")
    public void removeStudent(@ShellOption long id) {
        Student student = studentService.removeStudent(id);
        if (student == null) {
            System.out.println("A student with id " + id + " is not found");
        }
    }

    @ShellMethod(value = "getting all students", key = "get")
    public void getAllStudents() {
        studentService.getAllStudents().forEach(System.out::println);
    }

    @ShellMethod(value = "clearing student list", key = "del")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
        System.out.println("No students left");
    }
}
