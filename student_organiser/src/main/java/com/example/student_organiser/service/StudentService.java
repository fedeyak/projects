package com.example.student_organiser.service;

import com.example.student_organiser.dto.Student;
import com.example.student_organiser.events.AddStudentEvent;
import com.example.student_organiser.events.RemoveStudentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    private final ApplicationEventPublisher eventPublisher;

    public Student addStudent(String firstName, String lastName, int age) {
        long id = idGenerator.incrementAndGet();
        Student student = new Student(id, firstName, lastName, age);
        students.put(id, student);
        eventPublisher.publishEvent(new AddStudentEvent(student));
        return student;
    }

    public Student removeStudent(long id) {
        Student student = students.remove(id);
        if (student != null) {
            eventPublisher.publishEvent(new RemoveStudentEvent(id));
        }
        return student;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public void deleteAllStudents() {
        students.clear();
    }
}
